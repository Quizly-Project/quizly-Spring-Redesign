package Team9789.quizly_Spring.repository.token;

import Team9789.quizly_Spring.dto.token.RefreshTokenDto;

import java.util.Optional;

public interface TokenRepository {
    Optional<String> findRefreshUUIDByUserId(String userId);

    Optional<String> findRefreshByUUID(String uuid);

    void saveRefreshToken(RefreshTokenDto refreshTokenDto);

    void deleteRefreshToken(String uuid);

    void deleteRefreshUUID(String userId);

    Optional<String> findAccessUUIDByUserId(String userId);

    void saveAccessUUID(String userId, String uuid);

    void deleteAccessUUID(String userId);

}
