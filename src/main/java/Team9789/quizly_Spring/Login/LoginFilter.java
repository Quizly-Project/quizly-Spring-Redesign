package Team9789.quizly_Spring.Login;

import Team9789.quizly_Spring.dto.user.LoginDTO;
import Team9789.quizly_Spring.jwt.JwtProvider;
import Team9789.quizly_Spring.service.token.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationSuccessHandler successHandler;

    // 로그인 시도
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                LoginDTO loginDTO = mapper.readValue(request.getInputStream(), LoginDTO.class);
                String username = loginDTO.getUsername();
                String password = loginDTO.getPassword();

                System.out.println("username = " + username);
                System.out.println("password = " + password);
                if (username == null || password == null) {
                    throw new AuthenticationServiceException("Username or Password is null");
                }

                //Authentication 객체
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);

                // 부가 정보 Authentication 객체에 저장
                setDetails(request, authentication);
                return this.authenticationManager.authenticate(authentication);

            } catch (IOException e) {
                throw new AuthenticationServiceException("Invalid login request format", e);
            }
        }
        return super.attemptAuthentication(request, response);
    }

    // 인증 성공시
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, chain, authResult);
    }

}
