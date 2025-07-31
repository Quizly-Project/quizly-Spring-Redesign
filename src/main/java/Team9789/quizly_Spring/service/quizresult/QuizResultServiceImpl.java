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

    @Override
    @Transactional
    public String saveQuizResult(UserDto userDto, CreateQuizResultRequest request) {
        UserEntity user = getUserEntity(userDto);
        QuizGroup quizGroup = getQuizGroup(request);

        QuizResult quizResult = QuizResult.createQuizResult(
                user,
                quizGroup,
                request.getRoomCode(),
                request.getStudentResults()
        );

        return quizResultRepository.saveQuizResult(quizResult);
    }

    @Override
    public QuizResultDto getQuizResult(String roomCode) {
        QuizResult quizResult = getResult(roomCode);

        return new QuizResultDto(quizResult);
    }

    private QuizGroup getQuizGroup(CreateQuizResultRequest request) {
        Optional<QuizGroup> quizGroupOptional = quizGroupQueryRepository.getQuizGroupOne(request.getQuizGroupId());
        return quizGroupOptional.orElseThrow(NotFoundQuizGroupException::new);
    }

    private UserEntity getUserEntity(UserDto userDto) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(userDto.getUsername());
        return userOptional.orElseThrow(NotFoundUserException::new);
    }

    private QuizResult getResult(String roomCode) {
        Optional<QuizResult> quizResultOptional = quizResultRepository.getQuizResult(roomCode);
        return quizResultOptional.orElseThrow(NotFoundQuizResultException::new);
    }
}
