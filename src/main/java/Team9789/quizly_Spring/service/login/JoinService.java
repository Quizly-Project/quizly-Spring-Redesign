package Team9789.quizly_Spring.service.login;

import Team9789.quizly_Spring.dto.user.JoinDTO;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.exception.UserExistException;
import Team9789.quizly_Spring.repository.login.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//회원가입
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) throws UserExistException {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        Optional<UserEntity> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            UserEntity data = UserEntity.to(username, bCryptPasswordEncoder.encode(password), email);
            userRepository.save(data);
            return;
        }

        log.warn("이미 동일한 유저가 존재합니다.");
        throw new UserExistException("이미 동일한 유저가 존재합니다.");

    }
}
