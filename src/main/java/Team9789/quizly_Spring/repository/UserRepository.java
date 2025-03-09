package Team9789.quizly_Spring.repository;

import Team9789.quizly_Spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);

    //username을 받아 DB 테이블에서 회원을 조회
    UserEntity findByUsername(String username);

}
