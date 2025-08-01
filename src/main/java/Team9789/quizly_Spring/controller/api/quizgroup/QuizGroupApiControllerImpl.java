package Team9789.quizly_Spring.controller.api.quizgroup;

import Team9789.quizly_Spring.dto.quiz.QuizGroupDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import Team9789.quizly_Spring.exception.InvalidAuthorizationHeaderException;
import Team9789.quizly_Spring.jwt.JwtProvider;
import Team9789.quizly_Spring.service.quizgroup.QuizGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizGroupApiControllerImpl implements QuizGroupApiController {

    private final QuizGroupService quizGroupService;
    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;

    @GetMapping("/v1/users/{username}/quizgroups")
    public ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupByUsernameV1(
            @PathVariable("username") String username,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        List<QuizGroupDto> quizGroups = quizGroupService.getQuizGroupAllByUserName(username, offset, limit);

        return new ResponseEntity<>(new ResultDto<>("유저 이름으로 퀴즈 목록 조회", quizGroups), HttpStatus.OK);
    }

    @GetMapping("/v1/quizgroups")
    public ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupAllV1(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        List<QuizGroupDto> quizGroups = quizGroupService.getQuizGroupAll(offset, limit);
        return new ResponseEntity<>(new ResultDto<>("모든 퀴즈 목록 조회", quizGroups), HttpStatus.OK);
    }

    @GetMapping("/v1/quizgroups/{quizgroupId}")
    public ResponseEntity<ResultDto<QuizGroupDto>> findQuizGroupOneV1(@PathVariable("quizgroupId") Long quizgroupId) {
        QuizGroupDto quizGroupDto = quizGroupService.getQuizGroupOneById(quizgroupId);
        return new ResponseEntity<>(new ResultDto<>("특정 퀴즈 그룹 조회", quizGroupDto), HttpStatus.OK);
    }

    @PostMapping("/v1/quizgroups")
    public ResponseEntity<ResultDto<Long>> addQuizGroupV1(
            @RequestBody CreateQuizGroupRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
    ) throws InvalidAuthorizationHeaderException {
        int userId = getUserIdFrom(authorizationHeader);

        Long savedId = quizGroupService.saveQuizGroup(userId, request);
        return new ResponseEntity<>(new ResultDto<>("퀴즈 그룹 등록", savedId), HttpStatus.CREATED);
    }

    @PutMapping("/v1/quizgroups/{quizgroupId}")
    public ResponseEntity<ResultDto<Long>> updateQuizGroupV1(@RequestBody UpdateQuizGroupRequest request,
                                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
    ) throws InvalidAuthorizationHeaderException {
        int userId = getUserIdFrom(authorizationHeader);

        Long updatedId = quizGroupService.updateQuizGroup(userId, request);
        return new ResponseEntity<>(new ResultDto<>("퀴즈 그룹 수정", updatedId), HttpStatus.OK);
    }

    @DeleteMapping("/v1/quizgroups/{quizgroupId}")
    public ResponseEntity<ResultDto<Long>> removeQuizGroupV1(@PathVariable("quizgroupId") Long quizgroupId,
                                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
    ) throws InvalidAuthorizationHeaderException {
        int userId = getUserIdFrom(authorizationHeader);

        quizGroupService.removeQuizGroup(userId, quizgroupId);
        return new ResponseEntity<>(new ResultDto<>("퀴즈 그룹 삭제", 0L), HttpStatus.NO_CONTENT);
    }

    private int getUserIdFrom(String authorizationHeader) throws InvalidAuthorizationHeaderException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidAuthorizationHeaderException("authorizationHeader가 적절하지 않습니다.");
        }
        String accessToken = authorizationHeader.substring(7);
        return Integer.parseInt(jwtProvider.getUserId(accessToken));
    }
}
