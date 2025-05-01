package Team9789.quizly_Spring.jwt;

import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.exception.NotFindUserException;
import Team9789.quizly_Spring.repository.login.UserRepository;
import Team9789.quizly_Spring.service.token.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();

        // 권한 가져오기 
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = authorities.stream()
                .map(authority -> authority.toString())
                .collect(Collectors.toList());
        
        String refreshUUID = UUID.randomUUID().toString();

        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty()) throw new NotFindUserException();

        String userId = String.valueOf(userEntity.get().getId());
        
        tokenService.deleteRefreshTokenAndUUID(userId);
        tokenService.deleteAccessUUID(userId);

        String accessToken = jwtProvider.createAccessToken(userId, roles, username, refreshUUID);
        String refreshToken = jwtProvider.createRefreshToken(userId, refreshUUID);

        log.info("로그인 AccessToken 발급: "+ accessToken);
        log.info("로그인 RefreshToken 발급: "+ refreshToken);

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        response.addHeader(HttpHeaders.SET_COOKIE,
                ResponseCookie
                        .from("refreshToken", refreshToken)
                        .httpOnly(true)
                        .secure(true)
                        .path("/")
                        .maxAge(Duration.ofDays(1))
                        .sameSite("Strict")
                        .build()
                        .toString()
        );

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().flush();
    }
}
