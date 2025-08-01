package Team9789.quizly_Spring.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title("Quizly Server API 명세서")
                        .description("Quizly SpringBoot 서버에서 제공하는 RESTful API 목록")
                        .version("1.0.0"));
    }
}
