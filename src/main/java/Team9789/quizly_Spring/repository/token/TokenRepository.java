package Team9789.quizly_Spring.repository.token;

import Team9789.quizly_Spring.dto.token.RefreshTokenDto;

import java.util.Optional;

public interface TokenRepository {
    Optional<String> findRefreshByUserId(String userId);

    void saveRefreshToken(RefreshTokenDto refreshTokenDto);

    void deleteRefreshToken(String userId);
}
