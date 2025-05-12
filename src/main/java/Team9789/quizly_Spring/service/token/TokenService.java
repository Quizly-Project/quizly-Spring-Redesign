package Team9789.quizly_Spring.service.token;

import Team9789.quizly_Spring.dto.token.CreateRefreshToken;

import java.util.Optional;

public interface TokenService {
    Optional<String> findRefreshToken(String userId, String refreshToken);

    void saveRefreshToken(CreateRefreshToken createRefreshToken);

    void deleteRefreshToken(String userId);



}