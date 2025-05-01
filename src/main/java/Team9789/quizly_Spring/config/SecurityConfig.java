package Team9789.quizly_Spring.config;

import Team9789.quizly_Spring.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        LoginFilter loginFilter = new LoginFilter(authManager);
        loginFilter.setFilterProcessesUrl("/login");

        http
                .csrf(csrf -> csrf.disable()) // CSFR 보호기능 - 세션을 사용하지 않아 CSRF 위험이 없음
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 만들지 않고, 기존 세션도 사용하지 않도록 설정
                .formLogin(form -> form.disable()) // 폼 로그인 사용하지 않음
                .httpBasic(httpBasic -> httpBasic.disable()) // HTTP 기본 인증 방식 비활성화
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/join").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
