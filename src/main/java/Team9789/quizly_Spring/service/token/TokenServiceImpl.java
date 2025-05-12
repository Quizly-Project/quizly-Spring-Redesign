package Team9789.quizly_Spring.service.token;

import Team9789.quizly_Spring.dto.token.CreateRefreshToken;
import Team9789.quizly_Spring.dto.token.RefreshTokenDto;
import Team9789.quizly_Spring.repository.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
    public Optional<String> findRefreshToken(String userId, String refreshToken) {

        Optional<String> findRefreshToken = tokenRepository.findRefreshByUserId(userId);

        // RefreshToken이 없거나 일치하지 않는 경우
        if (findRefreshToken.isEmpty() || notEqualsRefreshToken(refreshToken, findRefreshToken)) {
            log.info("RefreshToken이 없거나 일치하지 않습니다.");
            return Optional.empty();
        }

        return findRefreshToken;
    }

    @Override
    @Transactional
    public void saveRefreshToken(CreateRefreshToken createRefreshToken) {
        tokenRepository.saveRefreshToken(RefreshTokenDto.to(createRefreshToken));
    }

    @Override
    public void deleteRefreshToken(String userId) {
        Optional<String> findRefreshToken = tokenRepository.findRefreshByUserId(userId);
        if (findRefreshToken.isEmpty()) {
            return;
        }
        tokenRepository.deleteRefreshToken(userId);

    }

    private static boolean notEqualsRefreshToken(String refreshToken, Optional<String> findRefreshToken) {
        return !refreshToken.equals(findRefreshToken.get());
    }

}
