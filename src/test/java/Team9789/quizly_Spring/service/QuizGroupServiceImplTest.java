package Team9789.quizly_Spring.service;

import Team9789.quizly_Spring.dto.QuizGroupDto;
import Team9789.quizly_Spring.dto.UserDto;
import Team9789.quizly_Spring.dto.request.quizgroup.*;
import Team9789.quizly_Spring.entity.QuizType;
import Team9789.quizly_Spring.entity.UserEntity;
import Team9789.quizly_Spring.repository.UserRepository;
import Team9789.quizly_Spring.service.quizgroup.QuizGroupService;
import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Rollback(value = false)
public class QuizGroupServiceImplTest {

    @Autowired
    QuizGroupService quizGroupService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void beforeEach() {
        //given
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("유영우");
        userRepository.save(userEntity);

    }

    @Test
    @Transactional
    public void 퀴즈그룹_저장_및_이름으로_조회() throws Exception {
        //given
        CreateQuizGroupRequest request = createQuizGroupRequest();

        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("유영우");

        //when
        Long savedId = quizGroupService.saveQuizGroup(userDto, request);

        QuizGroupDto quizGroups = quizGroupService.getQuizGroupByName("유영우", 0, 2).get(0);

        //then
        Assertions.assertEquals(userDto.getUsername(), quizGroups.getUserName());

        Assertions.assertEquals(request.getQuizzes().get(0).getExplanation(), quizGroups.getQuizzes().get(0).getExplanation());

        Assertions.assertEquals(request.getQuizzes().size(), quizGroups.getQuizzes().size());

        Assertions.assertEquals(
                request.getQuizzes().get(0).getOptions().get(0).getOptionText(),
                quizGroups.getQuizzes().get(0).getOptionDtos().get(0).getOptionText()
        );
        Assertions.assertEquals(
                request.getQuizzes().get(0).getOptions().size(),
                quizGroups.getQuizzes().get(0).getOptionDtos().size()
        );
    }

    @Test
    @Transactional
    public void 모든_퀴즈_그룹_조회() throws Exception {
        //given
        CreateQuizGroupRequest request = createQuizGroupRequest();

        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("유영우");

        //when
        Long savedId = quizGroupService.saveQuizGroup(userDto, request);

        QuizGroupDto quizGroups = quizGroupService.getQuizGroupAll(0, 2).get(0);

        //then
        Assertions.assertEquals(userDto.getUsername(), quizGroups.getUserName());

        Assertions.assertEquals(request.getQuizzes().get(0).getExplanation(), quizGroups.getQuizzes().get(0).getExplanation());

        Assertions.assertEquals(request.getQuizzes().size(), quizGroups.getQuizzes().size());

        Assertions.assertEquals(
                request.getQuizzes().get(0).getOptions().get(0).getOptionText(),
                quizGroups.getQuizzes().get(0).getOptionDtos().get(0).getOptionText()
        );
        Assertions.assertEquals(
                request.getQuizzes().get(0).getOptions().size(),
                quizGroups.getQuizzes().get(0).getOptionDtos().size()
        );
    }

    @Test
    @Transactional
    public void 퀴즈그룹_수정_테스트() throws Exception {
        //given
        CreateQuizGroupRequest request = createQuizGroupRequest();

        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("유영우");

        //when
        Long savedId = quizGroupService.saveQuizGroup(userDto, request);

        UpdateQuizGroupRequest request2 = updateQuizGroupRequest(savedId);

        quizGroupService.updateQuizGroup(userDto, request2);

        em.flush();
        em.clear();

        QuizGroupDto findQuizGroup = quizGroupService.getQuizGroupOne(savedId);

        //then
        Assertions.assertEquals(findQuizGroup.getQuizzes().get(0).getExplanation(), request2.getQuizzes().get(0).getExplanation());
        Assertions.assertEquals(findQuizGroup.getQuizzes().size(), request2.getQuizzes().size());

        Assertions.assertEquals(
                findQuizGroup.getQuizzes().get(0).getOptionDtos().get(0).getOptionText(),
                request2.getQuizzes().get(0).getQuizOptions().get(0).getOptionText()
        );

        Assertions.assertEquals(
                findQuizGroup.getQuizzes().get(0).getOptionDtos().size(),
                request2.getQuizzes().get(0).getQuizOptions().size()
        );

    }

    // 데이터 생성
    static CreateQuizGroupRequest createQuizGroupRequest() {

        CreateOptionRequest optionRequest1 = new CreateOptionRequest();
        optionRequest1.setOptionText("선택지 1");
        optionRequest1.setOptionNum(1);

        CreateOptionRequest optionRequest2 = new CreateOptionRequest();
        optionRequest2.setOptionText("선택지 2");
        optionRequest2.setOptionNum(2);


        CreateOptionRequest optionRequest11 = new CreateOptionRequest();
        optionRequest11.setOptionText("선택지 11");
        optionRequest11.setOptionNum(11);

        CreateOptionRequest optionRequest22 = new CreateOptionRequest();
        optionRequest22.setOptionText("선택지 22");
        optionRequest22.setOptionNum(22);

        CreateQuizRequest createQuizRequest1 = new CreateQuizRequest();
        createQuizRequest1.setCorrectAnswer("1");
        createQuizRequest1.setExplanation("문제 설명1");
        createQuizRequest1.setQuestion("문제1");
        createQuizRequest1.setQuizScore(10);
        createQuizRequest1.setQuizType(QuizType.OX_QUIZ);
        createQuizRequest1.getOptions().add(optionRequest1);
        createQuizRequest1.getOptions().add(optionRequest2);

        CreateQuizRequest createQuizRequest2 = new CreateQuizRequest();
        createQuizRequest2.setCorrectAnswer("11");
        createQuizRequest2.setExplanation("문제 설명11");
        createQuizRequest2.setQuestion("문제11");
        createQuizRequest2.setQuizScore(110);
        createQuizRequest2.setQuizType(QuizType.OX_QUIZ);
        createQuizRequest2.getOptions().add(optionRequest11);
        createQuizRequest2.getOptions().add(optionRequest22);

        CreateQuizGroupRequest request = new CreateQuizGroupRequest();
        request.setQuizTitle("퀴즈 그룹 제목");
        request.setQuizGroupDescription("퀴즈 그룹 설명");
        request.getQuizzes().add(createQuizRequest1);
        request.getQuizzes().add(createQuizRequest2);

        return request;
    }

    static UpdateQuizGroupRequest updateQuizGroupRequest(Long quizGroupId) {

        UpdateOptionRequest optionRequest1 = new UpdateOptionRequest();
        optionRequest1.setOptionId(1L);
        optionRequest1.setOptionText("수정 선택지1");

        UpdateOptionRequest optionRequest2 = new UpdateOptionRequest();
        optionRequest2.setOptionId(2L);
        optionRequest2.setOptionText("수정 선택지2");

        UpdateOptionRequest optionRequest11 = new UpdateOptionRequest();
        optionRequest11.setOptionId(3L);
        optionRequest11.setOptionText("수정 선택지3");

        UpdateOptionRequest optionRequest22 = new UpdateOptionRequest();
        optionRequest22.setOptionId(4L);
        optionRequest22.setOptionText("수정 선택지4");

        UpdateQuizRequest quizRequest1 = new UpdateQuizRequest();
        quizRequest1.setQuizId(1L);
        quizRequest1.setExplanation("수정1");
        quizRequest1.getQuizOptions().add(optionRequest1);
        quizRequest1.getQuizOptions().add(optionRequest2);

        UpdateQuizRequest quizRequest2 = new UpdateQuizRequest();
        quizRequest2.setQuizId(2L);
        quizRequest2.setExplanation("수정2");
        quizRequest2.getQuizOptions().add(optionRequest11);
        quizRequest2.getQuizOptions().add(optionRequest22);

        UpdateQuizGroupRequest request = new UpdateQuizGroupRequest();
        request.setQuizGroupId(quizGroupId);
        request.setQuizTitle("수정 제목");
        request.setQuizGroupDescription("수정 설명");
        request.getQuizzes().add(quizRequest1);
        request.getQuizzes().add(quizRequest2);

        return request;
    }

}