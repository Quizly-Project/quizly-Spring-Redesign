package Team9789.quizly_Spring.jwt;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class RefreshTokenGeneratorImpl implements RefreshTokenGenerator {

    private final SecureRandom secureRandom = new SecureRandom();                   // 암호학적으로 안전한 무작위 바이트 생성
    private final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding(); // 안전하게 인코딩하는 인코더

    @Override
    public String createOpaqueToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return encoder.encodeToString(bytes);
    }
}