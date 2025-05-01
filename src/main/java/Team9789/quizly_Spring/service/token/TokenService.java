package Team9789.quizly_Spring.service.token;

import Team9789.quizly_Spring.dto.token.CreateRefreshToken;

import java.util.Optional;

public interface TokenService {
    Optional<String> findRefreshToken(String userId, String uuid);

    void saveRefreshToken(CreateRefreshToken createRefreshToken);

    void deleteRefreshTokenAndUUID(String userId);

    Optional<String> findAccessUUID(String userId, String uuid);

    void saveAccessUUID(String userId, String uuid);

    void deleteAccessUUID(String userId);

}