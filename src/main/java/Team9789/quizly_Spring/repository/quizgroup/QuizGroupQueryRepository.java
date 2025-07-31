package Team9789.quizly_Spring.repository.quizgroup;


import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizOption;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizGroupQueryRepository {

    //== API Query ==//

    List<QuizGroup> getQuizGroupAllBy(String name, int offset, int limit);

    List<QuizGroup> getQuizGroupAll(int offset, int limit);

    //== API Query ==//


    //== Business Query ==//

    Optional<QuizGroup> getQuizGroupOne(Long quizGroupId);

    Optional<Quiz> getQuizOne(Long quizId);

    Optional<QuizOption> getQuizOptionOne(Long quizOptionId);

    //== Business Query ==//
}
