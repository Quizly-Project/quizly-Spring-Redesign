package Team9789.quizly_Spring.jwt;

import Team9789.quizly_Spring.entity.TokenStatus;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface JwtProvider {

    String createAccessToken(String userId, List<String> roles, String nickName);

    String createRefreshToken(String userId);

    TokenStatus validateToken(String token);

    String getUserId(String token);

    List<String> getRoles(String token);

    String getNickName(String token);

    Authentication getAuthentication(String token);
}

