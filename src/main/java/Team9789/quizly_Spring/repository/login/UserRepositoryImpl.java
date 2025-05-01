package Team9789.quizly_Spring.repository.login;

import Team9789.quizly_Spring.entity.UserEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager em;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return em.createQuery(" select ue from UserEntity ue" +
                        " where ue.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public void save(UserEntity userEntity) {
        em.persist(userEntity);
    }
}
