package Team9789.quizly_Spring.repository.quizgroup;

import Team9789.quizly_Spring.entity.QuizGroup;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuizGroupCommandRepositoryImpl implements QuizGroupCommandRepository {

    private final EntityManager em;

    //== Business Query ==//

    // 퀴즈 그룹 등록
    @Override
    public Long saveQuizGroup(QuizGroup quizGroup) {
        em.persist(quizGroup);
        return quizGroup.getId();
    }

    // 퀴즈 그룹 삭제
    @Override
    public void removeQuizGroup(QuizGroup quizGroup) {
        em.remove(quizGroup);
    }

    //== Business Query ==//
}
