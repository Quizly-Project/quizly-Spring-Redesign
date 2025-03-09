package Team9789.quizly_Spring.repository.quizresult;

import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.entity.QuizResult;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<QuizResult> getQuizResult(String roomCode) {
        return em.createQuery("select qr from QuizResult qr " +
                        " join fetch qr.userEntity " +
                        " join fetch qr.studentResult " +
                        " where qr.roomCode =:roomCode", QuizResult.class)
                .setParameter("roomCode", roomCode)
                .getResultList();
    }
}
