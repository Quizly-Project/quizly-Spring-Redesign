package Team9789.quizly_Spring.jwt;

import Team9789.quizly_Spring.entity.TokenStatus;
import Team9789.quizly_Spring.service.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = extractAccessToken(request);

        String uri = request.getRequestURI();
        if (uri.equals("/login") || uri.equals("/join") || uri.equals("/")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(existAccessToken(accessToken)){
            log.info("accessToken 없음");
            respondInvalidToken(response);
            return;
        }

        String userId = jwtProvider.getUserId(accessToken);

        TokenStatus tokenStatus = jwtProvider.validateToken(accessToken);
        log.info("사용자가 헤더에 전송한 accessToken 토큰 :" + accessToken);

        if (tokenStatus == TokenStatus.VALID) {
            log.info("토큰 유효");
            setAuthentication(accessToken);

        } else if (tokenStatus == TokenStatus.EXPIRED) {
            log.info("토큰 기간 만료");
            String refreshToken = extractRefreshToken(request);

            Optional<String> findRefreshToken = tokenService.findRefreshToken(userId, refreshToken);
            log.info("사용자가 쿠키에 전송한 RefreshToken: " + refreshToken);

            if (exist(refreshToken) && exist(findRefreshToken)) {
                List<String> roles = jwtProvider.getRoles(accessToken);
                String nickName = jwtProvider.getNickName(accessToken);

                accessToken = jwtProvider.createAccessToken(userId, roles, nickName);
                log.info("재발급한 AccessToken: " + accessToken);

                setAuthentication(accessToken);

                response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            } else {
                log.error("리프래시에 문제 발생 // 없음 or 유저 불일치");
                respondInvalidToken(response);
                return;
            }

        } else {
            log.error("유효하지 않은 AccessToken");
            respondInvalidToken(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private static boolean existAccessToken(String accessToken) {
        return accessToken == null || accessToken.isBlank();
    }

    private static boolean exist(String refreshToken) {
        return refreshToken != null;
    }

    private static boolean exist(Optional<String> findRefreshToken) {
        return !findRefreshToken.isEmpty();
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static void respondInvalidToken(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Unauthorized or Token Expired\"}");
    }

    private String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return "";
    }

    private String extractRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return "";

        for (Cookie cookie : cookies) {
            if ("refreshToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return "";
    }
}
