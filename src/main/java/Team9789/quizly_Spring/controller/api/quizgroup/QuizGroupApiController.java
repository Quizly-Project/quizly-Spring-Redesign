package Team9789.quizly_Spring.controller.api.quizgroup;

import Team9789.quizly_Spring.dto.quiz.QuizGroupDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@Tag(name="QuizGroupApiController", description = "퀴즈 그룹 정보를 CRUD하는 API")
public interface QuizGroupApiController {

    @Operation(summary = "유저이름으로 퀴즈그룹 목록 조회하는 API", description = "특정 사용자의 ID를 이용해서 퀴즈 그룹 목록 조회, 페이징 기능 제공")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 퀴즈 그룹 목록 조회"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupByUsernameV1(
            @PathVariable("username") String username,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    );

    @Operation(summary = "모든 퀴즈 그룹 목록을 조회하는 API", description = "모든 퀴즈 그룹 목록을 조회, 페이징 기능 제공")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 퀴즈 그룹 목록 조회"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    @GetMapping("/api/v1/quizgroups")
    ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupAllV1(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    );

    @Operation(summary = "퀴즈그룹 식별자를 이용해서 퀴즈그룹을 조회하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 특정 퀴즈 그룹 조회"),
            @ApiResponse(responseCode = "404", description = "해당 퀴즈 그룹을 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    ResponseEntity<ResultDto<QuizGroupDto>> findQuizGroupOneV1(@PathVariable("quizgroupId") Long quizgroupId);

    @Operation(summary = "퀴즈 그룹 등록을 위한 API", description = "사용자가 작성한 퀴즈 그룹, 퀴즈들, 선택지들을 DB에 저장하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "성공적으로 퀴즈 그룹을 등록했습니다."),
            @ApiResponse(responseCode = "403", description = "AuthorizationHeader에 문제가 있습니다."),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    ResponseEntity<ResultDto<Long>> addQuizGroupV1(@RequestParam("Quizgroup") CreateQuizGroupRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;

    @Operation(summary = "퀴즈 그룹 수정을 위한 API", description = "사용자가 수정한 퀴즈 그룹, 퀴즈들, 선택지들을 DB에 저장하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 퀴즈 그룹을 수정했습니다."),
            @ApiResponse(responseCode = "403", description = "AuthorizationHeader에 문제가 있습니다."),
            @ApiResponse(responseCode = "403", description = "수정자와 리소스 작성자가 다릅니다."),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    ResponseEntity<ResultDto<Long>> updateQuizGroupV1(@RequestParam("Quizgroup") UpdateQuizGroupRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;

    @Operation(summary = "퀴즈 그룹 삭제를 위한 API", description = "특정 퀴즈 그룹을 삭제하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 퀴즈 그룹을 등록했습니다."),
            @ApiResponse(responseCode = "403", description = "수정자와 리소스 작성자가 다릅니다."),
            @ApiResponse(responseCode = "403", description = "AuthorizationHeader에 문제가 있습니다."),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다."),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    ResponseEntity<ResultDto<Long>> removeQuizGroupV1(@PathVariable("quizgroupId") Long quizgroupId, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;
}
