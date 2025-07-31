package Team9789.quizly_Spring.service.quizgroup;

import Team9789.quizly_Spring.dto.quiz.QuizGroupDto;
import Team9789.quizly_Spring.dto.user.UserDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizGroupService {

    List<QuizGroupDto> getQuizGroupAllByUserName(String username, int offset, int limit);

    List<QuizGroupDto> getQuizGroupAll(int offset, int limit);

    QuizGroupDto getQuizGroupOneById(Long quizGroupId);

    Long saveQuizGroup(int userId, CreateQuizGroupRequest request) ;

    Long updateQuizGroup(int userId, UpdateQuizGroupRequest request);

    void removeQuizGroup(int userId, Long quizGroupId);
}
