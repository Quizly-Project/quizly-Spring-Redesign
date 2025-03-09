package Team9789.quizly_Spring.service.quizresult;

import Team9789.quizly_Spring.dto.QuizResultDto;
import Team9789.quizly_Spring.dto.UserDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizResultService {

    String saveQuizResult(UserDto userDto, CreateQuizResultRequest request);

    QuizResultDto getQuizResult(String roomCode);
}
