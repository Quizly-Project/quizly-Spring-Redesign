package Team9789.quizly_Spring.controller.api.quizgroup;

import Team9789.quizly_Spring.dto.QuizGroupDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.UserDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import Team9789.quizly_Spring.service.quizgroup.QuizGroupService;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/api/v1/users/{username}/quizgroups")
    public ResultDto<List<QuizGroupDto>> findQuizGroupByUsernameV1(
            @PathVariable("username") String username,
            @RequestParam("offset")  Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        List<QuizGroupDto> quizGroups = quizGroupService.getQuizGroupByName(username, offset, limit);

        return new ResultDto<List<QuizGroupDto>>("유저 이름으로 퀴즈 목록 조회", quizGroups);
    }

    /**
     * 모든 퀴즈 목록 조회
     * 페이지 기능 추가되어 있음
     */
    @GetMapping("/api/v1/quizgroups")
    public ResultDto<List<QuizGroupDto>> findQuizGroupAllV1(
            @RequestParam("offset")  Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        List<QuizGroupDto> quizGroups = quizGroupService.getQuizGroupAll(offset, limit);
        return new ResultDto<List<QuizGroupDto>>("모든 퀴즈 목록 조회", quizGroups);
    }

    /**
     * 특정 퀴즈 그룹 조회
     * 퀴즈 그룹 아이디를 이용해서 퀴즈 그룹 조회
     */
    @GetMapping("/api/v1/quizgroups/{quizgroupId}")
    public ResultDto<QuizGroupDto> findQuizGroupOneV1(@PathVariable("quizgroupId") Long quizgroupId) {
        QuizGroupDto quizGroupDto = quizGroupService.getQuizGroupOne(quizgroupId);
        return new ResultDto<QuizGroupDto>("특정 퀴즈 그룹 하나 조회", quizGroupDto);
    }

    /**
     * 퀴즈 그룹 등록하기
     * 등록된 퀴즈 그룹 ID 반환
     */
    @PostMapping("/api/v1/quizgroups")
    public ResultDto<Long> addQuizGroupV1(@RequestParam("Quizgroup") CreateQuizGroupRequest request) {
        // TODO: JWT에서 값을 꺼내오는 작업 해야 함
        // 임시로 데이터 사용
        UserDto userDto = createTemporaryUserEntity();
        Long savedId = quizGroupService.saveQuizGroup(userDto ,request);
        return new ResultDto<Long>("퀴즈 그룹 추가", savedId);
    }

    /**
     * 퀴즈 그룹 수정하기
     * 수정된 퀴즈 그룹 ID 반환
     */
    @PutMapping("/api/v1/quizgroups/{quizgroupId}")
    public ResultDto<Long> updateQuizGroupV1(@RequestParam("Quizgroup") UpdateQuizGroupRequest request) {
        // TODO: JWT에서 값을 꺼내오는 작업 해야 함
        // 임시로 데이터 사용
        UserDto userDto = createTemporaryUserEntity();
        Long updatedId = quizGroupService.updateQuizGroup(userDto, request);
        return new ResultDto<Long>("퀴즈 그룹 수정", updatedId);
    }

    /**
     * 퀴즈 그룹 삭제하기
     * 삭제된 퀴즈 그룹 ID 반환
     */
    @DeleteMapping("/api/v1/quizgroups/{quizgroupId}")
    public ResultDto<Long> removeQuizGroupV1(@PathVariable("quizgroupId") Long quizgroupId) {
        // TODO: JWT에서 값을 꺼내오는 작업 해야 함
        // 임시로 데이터 사용
        UserDto userDto = createTemporaryUserEntity();

        Long removeId = quizGroupService.removeQuizGroup(userDto, quizgroupId);
        return new ResultDto<Long>("퀴즈 그룹을 삭제합니다.", removeId);
    }

    // 임시 유저 데이터 사용
    private UserDto createTemporaryUserEntity() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("홍길동");
        return userDto;
    }

}
