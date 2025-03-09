package Team9789.quizly_Spring.repository.quizresult;

import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.entity.QuizResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository {

    String saveQuizResult(QuizResult quizResult);

    List<QuizResult> getQuizResult(String roomCode);
}
