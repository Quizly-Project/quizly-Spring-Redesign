package Team9789.quizly_Spring.repository.quizresult;

import Team9789.quizly_Spring.entity.QuizResult;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizResultRepository {

    String saveQuizResult(QuizResult quizResult);

    Optional<QuizResult> getQuizResult(String roomCode);
}
