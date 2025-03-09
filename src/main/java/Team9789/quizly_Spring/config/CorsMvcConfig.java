package Team9789.quizly_Spring.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
                .allowedOriginPatterns("*")  // 모든 도메인을 허용
                .allowedMethods("*")  // 모든 HTTP 메서드를 허용
                .allowedHeaders("*")  // 모든 헤더를 허용
                .allowCredentials(true);
    }
}
