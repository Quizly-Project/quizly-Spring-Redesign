package Team9789.quizly_Spring.repository.quizgroup;


import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizOption;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizGroupQueryRepository {

    //== API Query ==//

    List<QuizGroup> getQuizGroupByUserName(String name, int offset, int limit);

    List<QuizGroup> getQuizGroupAll(int offset, int limit);

    //== API Query ==//


    //== Business Query ==//

    List<QuizGroup> getQuizGroupOne(Long quizGroupId);

    List<Quiz> getQuizOne(Long quizId);

    QuizOption getQuizOptionOne(Long quizOptionId);

    //== Business Query ==//
}
