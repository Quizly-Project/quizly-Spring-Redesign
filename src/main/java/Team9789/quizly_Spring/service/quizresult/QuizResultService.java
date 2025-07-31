package Team9789.quizly_Spring.service.quizresult;

import Team9789.quizly_Spring.dto.quiz.QuizResultDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import org.springframework.stereotype.Service;

@Service
public interface QuizResultService {

    String saveQuizResult(int userId, CreateQuizResultRequest request);

    QuizResultDto getQuizResult(String roomCode);
}
