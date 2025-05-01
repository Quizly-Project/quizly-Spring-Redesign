package Team9789.quizly_Spring.service.login;

import Team9789.quizly_Spring.dto.JoinDTO;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.repository.login.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//회원가입
@Service
@Transactional
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        Optional<UserEntity> result = userRepository.findByUsername(username);

        if (result.isEmpty()) {
            UserEntity data = UserEntity.to(username, bCryptPasswordEncoder.encode(password), email);
            userRepository.save(data);
        }
    }
}
