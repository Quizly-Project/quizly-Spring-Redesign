package Team9789.quizly_Spring.repository.quizgroup;

import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizOption;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuizGroupQueryRepositoryImpl implements QuizGroupQueryRepository{

    private final EntityManager em;

    //== API Query ==//

    /**
     * 페이징 개수 만큼 QuizGroup을 조회한다.
     * 이때, 페치 조인을 사용해서 user 정보까지 함께 가져온다.
     * 컬렉션과 컬렉션의 컬렉션의 경우 지연로딩으로 조회한다.
     * 이때, @BatchSize(size = 100)를 사용해서 IN 쿼리로 가져온다.
     * 최소의 경우 쿼리 3번으로 모든 데이터를 불러온다.
     */
    @Override
    public List<QuizGroup> getQuizGroupByUserName(String name, int offset, int limit) {
          return em.createQuery("select qg from QuizGroup qg " +
                        " join fetch qg.userEntity " +
                        " where qg.userEntity.username =: username", QuizGroup.class)
                .setParameter("username", name)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<QuizGroup> getQuizGroupAll(int offset, int limit) {
         return em.createQuery("select qg from QuizGroup qg " +
                        " join fetch qg.userEntity ", QuizGroup.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    //== API Query ==//

    //== Business Query ==//

    /**
     * 퀴즈 그룹 정보에 대하여 fetch join을 수행
     */
    @Override
    public List<QuizGroup> getQuizGroupOne(Long quizGroupId) {
        return em.createQuery("select qg from QuizGroup qg " +
                        " join fetch qg.userEntity " +
                        " join fetch qg.quizzes " +
                        " where qg.id =:quizGroupId ", QuizGroup.class)
                .setParameter("quizGroupId", quizGroupId)
                .getResultList();
    }

    @Override
    public List<Quiz> getQuizOne(Long quizid) {
        return em.createQuery("select q from Quiz q " +
                        " join fetch q.quizOptions " +
                        " where q.id =:quizId ")
                .setParameter("quizId", quizid)
                .getResultList();
    }

    @Override
    public QuizOption getQuizOptionOne(Long quizOptionId) {
        return em.find(QuizOption.class, quizOptionId);
    }

    //== Business Query ==//
}
