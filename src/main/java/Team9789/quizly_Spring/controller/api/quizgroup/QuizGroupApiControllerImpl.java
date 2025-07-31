package Team9789.quizly_Spring.controller.api.quizgroup;

import Team9789.quizly_Spring.dto.quiz.QuizGroupDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.user.UserDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import Team9789.quizly_Spring.service.quizgroup.QuizGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizGroupApiControllerImpl implements QuizGroupApiController{

    private final QuizGroupService quizGroupService;

    /**
     * 유저 이름으로 퀴즈 목록 조회
     * 페이징 기능 추가되어 있음
     */
    @GetMapping("/v1/users/{username}/quizgroups")
    public ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupByUsernameV1(
            @PathVariable("username") String username,
            @RequestParam("offset")  Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        List<QuizGroupDto> quizGroups = quizGroupService.getQuizGroupAllByUserName(username, offset, limit);

        return new ResponseEntity<ResultDto<List<QuizGroupDto>>>(new ResultDto<>("유저 이름으로 퀴즈 목록 조회",quizGroups), HttpStatus.OK);
    }

    /**
     * 모든 퀴즈 목록 조회
     * 페이지 기능 추가되어 있음
     */
    @GetMapping("/v1/quizgroups")
    public ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupAllV1(
            @RequestParam("offset")  Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        List<QuizGroupDto> quizGroups = quizGroupService.getQuizGroupAll(offset, limit);
        return new ResponseEntity<ResultDto<List<QuizGroupDto>>>(new ResultDto<>("모든 퀴즈 목록 조회",quizGroups), HttpStatus.OK);
    }

    /**
     * 특정 퀴즈 그룹 조회
     * 퀴즈 그룹 아이디를 이용해서 퀴즈 그룹 조회
     */
    @GetMapping("/v1/quizgroups/{quizgroupId}")
    public ResponseEntity<ResultDto<QuizGroupDto>> findQuizGroupOneV1(@PathVariable("quizgroupId") Long quizgroupId) {
        QuizGroupDto quizGroupDto = quizGroupService.getQuizGroupOneById(quizgroupId);
        return new ResponseEntity<ResultDto<QuizGroupDto>>(new ResultDto<>("특정 퀴즈 그룹 조회", quizGroupDto), HttpStatus.OK);
    }

    /**
     * 퀴즈 그룹 등록하기
     * 등록된 퀴즈 그룹 ID 반환
     */
    @PostMapping("/v1/quizgroups")
    public ResponseEntity<ResultDto<Long>> addQuizGroupV1(@RequestBody CreateQuizGroupRequest request) {
        // TODO: JWT에서 값을 꺼내오는 작업 해야 함
        // 임시로 데이터 사용
        UserDto userDto = createTemporaryUserEntity();
        Long savedId = quizGroupService.saveQuizGroup(userDto ,request);
        return new ResponseEntity<ResultDto<Long>>(new ResultDto<>("퀴즈 그룹 등록", savedId), HttpStatus.CREATED);
    }

    /**
     * 퀴즈 그룹 수정하기
     * 수정된 퀴즈 그룹 ID 반환
     */
    @PutMapping("/v1/quizgroups/{quizgroupId}")
    public ResponseEntity<ResultDto<Long>> updateQuizGroupV1(@RequestBody UpdateQuizGroupRequest request) {
        // TODO: JWT에서 값을 꺼내오는 작업 해야 함
        // 임시로 데이터 사용
        UserDto userDto = createTemporaryUserEntity();
        Long updatedId = quizGroupService.updateQuizGroup(userDto, request);
        return new ResponseEntity<ResultDto<Long>>(new ResultDto<>("퀴즈 그룹 수정", updatedId), HttpStatus.OK);
    }

    /**
     * 퀴즈 그룹 삭제하기
     * 삭제된 퀴즈 그룹 ID 반환
     */
    @DeleteMapping("/v1/quizgroups/{quizgroupId}")
    public ResponseEntity<ResultDto<Long>> removeQuizGroupV1(@PathVariable("quizgroupId") Long quizgroupId) {
        // TODO: JWT에서 값을 꺼내오는 작업 해야 함
        // 임시로 데이터 사용
        UserDto userDto = createTemporaryUserEntity();

        quizGroupService.removeQuizGroup(userDto, quizgroupId);
        return new ResponseEntity<ResultDto<Long>>(new ResultDto<>("퀴즈 그룹 삭제", 0L), HttpStatus.NO_CONTENT);
    }

    // 임시 유저 데이터 사용
    private UserDto createTemporaryUserEntity() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("홍길동");
        return userDto;
    }

}
