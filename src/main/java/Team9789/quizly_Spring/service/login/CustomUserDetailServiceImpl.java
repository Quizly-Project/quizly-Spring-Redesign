package Team9789.quizly_Spring.service.login;

import Team9789.quizly_Spring.dto.CustomUserDetails;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.repository.login.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("Not Found User");
        }

        return new CustomUserDetails(userEntity.get());
    }
}
