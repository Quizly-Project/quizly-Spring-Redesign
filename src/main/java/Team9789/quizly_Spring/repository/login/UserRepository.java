package Team9789.quizly_Spring.repository.login;

import Team9789.quizly_Spring.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsername(String username);

    void save(UserEntity userEntity);
}
