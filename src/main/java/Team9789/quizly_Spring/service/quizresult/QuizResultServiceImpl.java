package Team9789.quizly_Spring.service.quizresult;

import Team9789.quizly_Spring.dto.QuizResultDto;
import Team9789.quizly_Spring.dto.UserDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizResult;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.exception.NotEqualsUserException;
import Team9789.quizly_Spring.exception.NotFindQuizGroupException;
import Team9789.quizly_Spring.exception.NotFindQuizResultException;
import Team9789.quizly_Spring.exception.NotFindUserException;
import Team9789.quizly_Spring.repository.UserRepository;
import Team9789.quizly_Spring.repository.quizgroup.QuizGroupQueryRepository;
import Team9789.quizly_Spring.repository.quizresult.QuizResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        UserEntity userEntity = userRepository.getById(userDto.getUserId());
        // FIXME: 적절한 비교 방법을 찾아볼 것
        if (userEntity == null) throw new NotFindUserException();

        QuizGroup quizGroup = quizGroupQueryRepository.getQuizGroupOne(request.getQuizGroupId())
                .stream().findFirst().orElseThrow(()-> new NotFindQuizGroupException());

        QuizResult quizResult = QuizResult.createQuizResult(
                userEntity, quizGroup, request.getRoomCode(), request.getStudentResults());

        return quizResultRepository.saveQuizResult(quizResult);
    }

    /**
     * 퀴즈 결과 가져오기
     * 퀴즈 결과가 있으면 결과를 반환, 없으면 NotFindQuizResult() 예외 발생
     */
    @Override
    public QuizResultDto getQuizResult(String roomCode) {
        QuizResult quizResult = quizResultRepository.getQuizResult(roomCode)
                .stream().findFirst().orElseThrow(() -> new NotFindQuizResultException());
        return new QuizResultDto(quizResult);
    }
}
