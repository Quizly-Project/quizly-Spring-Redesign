package Team9789.quizly_Spring.service.quizresult;

import Team9789.quizly_Spring.dto.QuizResultDto;
import Team9789.quizly_Spring.dto.UserDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizResult;
import Team9789.quizly_Spring.entity.UserEntity;
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
     */
    @Override
    @Transactional
    public String saveQuizResult(UserDto userDto, CreateQuizResultRequest request) {
        UserEntity userEntity = userRepository.getById(userDto.getUserId());
        QuizGroup quizGroup = quizGroupQueryRepository.findQuizGroupOne(request.getQuizGroupId()).get(0);

        QuizResult quizResult = QuizResult.createQuizResult(
                userEntity, quizGroup, request.getRoomCode(), request.getStudentResults());

        return quizResultRepository.saveQuizResult(quizResult);
    }

    /**
     * 퀴즈 결과 가져오기
     */
    @Override
    public QuizResultDto getQuizResult(String roomCode) {
        QuizResult quizResult = quizResultRepository.getQuizResult(roomCode).get(0);
        return new QuizResultDto(quizResult);
    }
}
