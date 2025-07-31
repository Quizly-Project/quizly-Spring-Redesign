package Team9789.quizly_Spring.service.quizgroup;

import Team9789.quizly_Spring.exception.*;
import Team9789.quizly_Spring.dto.quiz.QuizGroupDto;
import Team9789.quizly_Spring.dto.user.UserDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateOptionRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizRequest;
import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizOption;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.repository.login.UserRepository;
import Team9789.quizly_Spring.repository.quizgroup.QuizGroupCommandRepository;
import Team9789.quizly_Spring.repository.quizgroup.QuizGroupQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizGroupServiceImpl implements QuizGroupService {

    private final QuizGroupQueryRepository quizGroupQueryRepository;
    private final QuizGroupCommandRepository quizGroupCommandRepository;
    private final UserRepository userRepository;

    @Override
    public List<QuizGroupDto> getQuizGroupAllByUserName(String username, int offset, int limit) {
        List<QuizGroup> quizGroups = quizGroupQueryRepository.getQuizGroupAllByUserName(username, offset, limit);
        return quizGroupToDto(quizGroups);
    }

    @Override
    public List<QuizGroupDto> getQuizGroupAll(int offset, int limit) {
        List<QuizGroup> quizGroups = quizGroupQueryRepository.getQuizGroupAll(offset, limit);
        return quizGroupToDto(quizGroups);
    }

    @Override
    public QuizGroupDto getQuizGroupOneById(Long quizGroupId) throws NotFoundQuizGroupException {
        Optional<QuizGroup> quizGroupOptional = quizGroupQueryRepository.getQuizGroupOne(quizGroupId);
        QuizGroup quizGroup = quizGroupOptional.orElseThrow(NotFoundQuizGroupException::new);

        return new QuizGroupDto(quizGroup);
    }

    @Override
    @Transactional
    public Long saveQuizGroup(UserDto userDto, CreateQuizGroupRequest createQuizGroupData) throws NotFoundUserException {
        Optional<UserEntity> userOptional = userRepository.findByUsername(userDto.getUsername());
        UserEntity user = userOptional.orElseThrow(NotFoundUserException::new);

        QuizGroup quizGroups = QuizGroup.createQuizGroup(user, createQuizGroupData.getQuizTitle(), createQuizGroupData.getQuizGroupDescription(), createQuizGroupData.getQuizzes());

        return quizGroupCommandRepository.saveQuizGroup(quizGroups);
    }

    @Override
    @Transactional
    public Long updateQuizGroup(UserDto userDto, UpdateQuizGroupRequest updateQuizGroupData) throws ResourceOwnerMismatchException {
        Optional<QuizGroup> quizGroupOptional = quizGroupQueryRepository.getQuizGroupOne(updateQuizGroupData.getQuizGroupId());
        QuizGroup quizGroup = quizGroupOptional.orElseThrow(NotFoundQuizGroupException::new);

        if (quizGroup.isNotOwner(userDto.getUserId())) throw new ResourceOwnerMismatchException("수정자와 리소스 작성자가 다릅니다.");

        quizGroup.updateQuizGroup(updateQuizGroupData.getQuizTitle(), updateQuizGroupData.getQuizGroupDescription());

        updateQuizzesFrom(updateQuizGroupData);

        return quizGroup.getId();
    }

    @Override
    @Transactional
    public void removeQuizGroup(UserDto userDto, Long quizGroupId) throws NotFoundQuizGroupException {
        Optional<QuizGroup> quizGroupOptional = quizGroupQueryRepository.getQuizGroupOne(quizGroupId);

        QuizGroup quizGroup = quizGroupOptional.orElseThrow(NotFoundQuizGroupException::new);

        if (quizGroup.isNotOwner(userDto.getUserId())) {
            throw new ResourceOwnerMismatchException("수정자와 리소스 작성자가 다릅니다.");
        }

        quizGroupCommandRepository.removeQuizGroup(quizGroup);
    }

    private List<QuizGroupDto> quizGroupToDto(List<QuizGroup> quizGroups) {
        return quizGroups.stream()
                .map(QuizGroupDto::new)
                .toList();
    }

    private void updateQuizzesFrom(UpdateQuizGroupRequest updateData) throws NotFoundQuizException,  NotFoundQuizOptionException {
        for (UpdateQuizRequest quiz : updateData.getQuizzes()) {
            Optional<Quiz> quizOptional = quizGroupQueryRepository.getQuizOne(quiz.getQuizId());

            Quiz currQuiz = quizOptional.orElseThrow(NotFoundQuizException::new);

            currQuiz.updateQuiz(
                    quiz.getQuestion(),
                    quiz.getCorrectAnswer(),
                    quiz.getExplanation(),
                    quiz.getQuizScore()
            );

            for (UpdateOptionRequest option : quiz.getQuizOptions()) {
                Optional<QuizOption> result = quizGroupQueryRepository.getQuizOptionOne(option.getOptionId());

                QuizOption currOption = result.orElseThrow(NotFoundQuizOptionException::new);

                currOption.updateQuizOption(
                        option.getOptionText(),
                        option.getOptionNum()
                );
            }
        }
    }
}
