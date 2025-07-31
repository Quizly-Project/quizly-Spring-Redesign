package Team9789.quizly_Spring.service.quizresult;

import Team9789.quizly_Spring.dto.quiz.QuizResultDto;
import Team9789.quizly_Spring.dto.user.UserDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizResult;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.exception.NotFoundQuizGroupException;
import Team9789.quizly_Spring.exception.NotFoundQuizResultException;
import Team9789.quizly_Spring.exception.NotFoundUserException;
import Team9789.quizly_Spring.repository.login.UserRepository;
import Team9789.quizly_Spring.repository.quizgroup.QuizGroupQueryRepository;
import Team9789.quizly_Spring.repository.quizresult.QuizResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizResultServiceImpl implements QuizResultService {

    private final QuizResultRepository quizResultRepository;
    private final UserRepository userRepository;
    private final QuizGroupQueryRepository quizGroupQueryRepository;

    /**
     * 퀴즈 결과 저장
     * 결과를 저장할 사용자가 없다면 NotFindUserException 예외 발생
     * 관련된 퀴즈가 없다면, NotFindQuizGroupException 예외 발생
     * 문제 없으면 퀴즈 결과를 저장
     */
    @Override
    @Transactional
    public String saveQuizResult(UserDto userDto, CreateQuizResultRequest request) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(userDto.getUsername());
        UserEntity user = userOptional.orElseThrow(NotFoundUserException::new);

        Optional<QuizGroup> quizGroupOptional = quizGroupQueryRepository.getQuizGroupOne(request.getQuizGroupId());
        QuizGroup quizGroup = quizGroupOptional.orElseThrow(NotFoundQuizGroupException::new);

        QuizResult quizResult = QuizResult.createQuizResult(
                user,
                quizGroup,
                request.getRoomCode(),
                request.getStudentResults()
        );

        return quizResultRepository.saveQuizResult(quizResult);
    }

    /**
     * 퀴즈 결과 가져오기
     * 퀴즈 결과가 있으면 결과를 반환, 없으면 NotFindQuizResult() 예외 발생
     */
    @Override
    public QuizResultDto getQuizResult(String roomCode) {
        Optional<QuizResult> quizResultOptional = quizResultRepository.getQuizResult(roomCode);
        QuizResult quizResult = quizResultOptional.orElseThrow(NotFoundQuizResultException::new);

        return new QuizResultDto(quizResult);
    }
}
