package Team9789.quizly_Spring.repository.token;

import Team9789.quizly_Spring.dto.token.RefreshTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisTokenRepositoryImpl implements TokenRepository {

    private final RedisOperations<String, Object> redisOperations;

    private static final String PREFIX_REFRESH_USER = "refresh:user:";

    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidity;

    @Override
    public Optional<String> findRefreshByUserId(String userId) {
        String findRefreshToken = (String) redisOperations.opsForValue().get(PREFIX_REFRESH_USER + userId);
        return Optional.ofNullable(findRefreshToken);
    }

    @Override
    public void saveRefreshToken(RefreshTokenDto refreshTokenDto) {
        redisOperations.opsForValue().set(
                PREFIX_REFRESH_USER + refreshTokenDto.getUserId(),
                refreshTokenDto.getOpaqueToken(),
                refreshTokenValidity, TimeUnit.HOURS
        );
    }

    @Override
    public void deleteRefreshToken(String userId) {
        redisOperations.delete(PREFIX_REFRESH_USER + userId);
    }


}