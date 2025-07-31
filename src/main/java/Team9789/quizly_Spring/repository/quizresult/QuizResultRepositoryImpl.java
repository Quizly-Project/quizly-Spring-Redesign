package Team9789.quizly_Spring.repository.quizresult;

import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.entity.QuizResult;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuizResultRepositoryImpl implements QuizResultRepository {

    private final EntityManager em;

    @Override
    public String saveQuizResult(QuizResult quizResult) {
        em.persist(quizResult);
        return quizResult.getRoomCode();
    }

    @Override
    public Optional<QuizResult> getQuizResult(String roomCode) {
        List<QuizResult> result = em.createQuery("select qr from QuizResult qr " +
                        " join fetch qr.userEntity " +
                        " join fetch qr.studentResults " +
                        " where qr.roomCode =:roomCode", QuizResult.class)
                .setParameter("roomCode", roomCode)
                .getResultList();

        return result.stream().findFirst();
    }
}
