package Team9789.quizly_Spring.repository.quizgroup;

import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizOption;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public List<QuizGroup> getQuizGroupAllBy(String name, int offset, int limit) {
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
        List<Long> ids = em.createQuery("select qg.id from QuizGroup qg ", Long.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        return em.createQuery("select qg from QuizGroup qg " +
                        " join fetch qg.userEntity " +
                        " where qg.id in :ids", QuizGroup.class)
                .setParameter("ids", ids)
                .getResultList();
    }
    //== API Query ==//

    //== Business Query ==//

    /**
     * 퀴즈 그룹 정보에 대하여 fetch join을 수행
     */
    @Override
    public Optional<QuizGroup> getQuizGroupOne(Long quizGroupId) {
        List<QuizGroup> result = em.createQuery("select qg from QuizGroup qg " +
                        " join fetch qg.userEntity " +
                        " join fetch qg.quizzes " +
                        " where qg.id =:quizGroupId ", QuizGroup.class)
                .setParameter("quizGroupId", quizGroupId)
                .getResultList();

        return result.stream().findFirst();
    }

    @Override
    public Optional<Quiz> getQuizOne(Long quizid) {
        List<Quiz> result = em.createQuery("select q from Quiz q " +
                        " join fetch q.quizOptions " +
                        " where q.id =:quizId ", Quiz.class)
                .setParameter("quizId", quizid)
                .getResultList();

        return result.stream().findFirst();
    }

    @Override
    public Optional<QuizOption> getQuizOptionOne(Long quizOptionId) {
        QuizOption result = em.find(QuizOption.class, quizOptionId);
        return Optional.of(result);
    }

    //== Business Query ==//
}
