package Team9789.quizly_Spring.service.quizgroup;

import Team9789.quizly_Spring.exception.NotEqualsUserException;
import Team9789.quizly_Spring.dto.QuizGroupDto;
import Team9789.quizly_Spring.dto.UserDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateOptionRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizRequest;
import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizGroup;
import Team9789.quizly_Spring.entity.QuizOption;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.exception.NotFindQuizGroupException;
import Team9789.quizly_Spring.repository.quizgroup.QuizGroupCommandRepository;
import Team9789.quizly_Spring.repository.UserRepository;
import Team9789.quizly_Spring.repository.quizgroup.QuizGroupQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuizGroupServiceImpl implements QuizGroupService{

    private final QuizGroupQueryRepository quizGroupQueryRepository;
    private final QuizGroupCommandRepository quizGroupCommandRepository;
    private final UserRepository userRepository;

    /**
     * 생성 및 수정 같은 일부 데이터만 옮기는 경우 API에 맞춘 DTO로 반환한다.
     * Ex) CreateQuizGroupRequest, CreateQuizGroupResponse
     */

    /**
     * 퀴즈 생성자 이름으로 퀴즈 그룹 조회 (페이징)
     */
    @Override
    public List<QuizGroupDto> getQuizGroupByName(String username, int offset, int limit) {
        List<QuizGroup> quizGroups = quizGroupQueryRepository.getQuizGroupByUserName(username, offset, limit);

        List<QuizGroupDto> quizGroupDtos = quizGroups.stream()
                .map(quizGroup -> new QuizGroupDto(quizGroup))
                .collect(Collectors.toList());

        return quizGroupDtos;
    }

    /**
     * 모든 퀴즈 조회 (페이징)
     */
    @Override
    public List<QuizGroupDto> getQuizGroupAll(int offset, int limit) {
        List<QuizGroup> quizGroups = quizGroupQueryRepository.getQuizGroupAll(offset, limit);

        List<QuizGroupDto> quizGroupDtos = quizGroups.stream()
                .map(quizGroup -> new QuizGroupDto(quizGroup))
                .collect(Collectors.toList());

        return quizGroupDtos;
    }

    @Override
    public QuizGroupDto getQuizGroupOne(Long quizGroupId) {
        QuizGroup quizGroup = quizGroupQueryRepository.getQuizGroupOne(quizGroupId).get(0);

        return new QuizGroupDto(quizGroup);
    }

    /**
     * 퀴즈 그룹 저장하기
     */
    @Override
    @Transactional
    public Long saveQuizGroup(UserDto userDto, CreateQuizGroupRequest request) {
        UserEntity userEntity = userRepository.findById(userDto.getUserId()).get();

        QuizGroup quizGroups = QuizGroup.createQuizGroup(userEntity, request.getQuizTitle(), request.getQuizGroupDescription(), request.getQuizzes());
        return quizGroupCommandRepository.saveQuizGroup(quizGroups);
    }

    /**
     * 퀴즈 그룹 수정하기
     */
    @Override
    @Transactional
    public Long updateQuizGroup(UserDto userDto, UpdateQuizGroupRequest request) throws NotEqualsUserException{
        QuizGroup findQuizGroup = quizGroupQueryRepository.getQuizGroupOne(request.getQuizGroupId()).get(0);

        if (!findQuizGroup.getId().equals(Long.valueOf(userDto.getUserId()))) return 1L;

        findQuizGroup.updateQuizGroup(request.getQuizTitle(), request.getQuizGroupDescription());

        for (UpdateQuizRequest quiz : request.getQuizzes()) {
            Quiz currQuiz = quizGroupQueryRepository.getQuizOne(quiz.getQuizId()).get(0);

            currQuiz.updateQuiz(
                    quiz.getQuestion(),
                    quiz.getCorrectAnswer(),
                    quiz.getExplanation(),
                    quiz.getQuizScore()
            );

            for (UpdateOptionRequest option : quiz.getQuizOptions()) {
                QuizOption currOption = quizGroupQueryRepository.getQuizOptionOne(option.getOptionId());

                currOption.updateQuizOption(
                        option.getOptionText(),
                        option.getOptionNum()
                );
            }
        }
        return findQuizGroup.getId();
    }

    /**
     * 퀴즈 그룹 제거하기
     */
    @Override
    @Transactional
    public Long removeQuizGroup(UserDto userDto, Long quizGroupId) {
        QuizGroup findQuizGroup = quizGroupQueryRepository.getQuizGroupOne(quizGroupId)
                .stream().findFirst().orElseThrow(() -> new EntityNotFoundException("삭제할 데이터가 존재하지 않습니다."));

        if (!findQuizGroup.getId().equals(Long.valueOf(userDto.getUserId())))
            throw new NotEqualsUserException("사용자가 일치하지 않습니다.");

        return quizGroupCommandRepository.removeQuizGroup(findQuizGroup);
    }
}
