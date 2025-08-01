package Team9789.quizly_Spring.controller.api.quizresult;

import Team9789.quizly_Spring.dto.quiz.QuizResultDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@Tag(name="QuizResultApiController", description = "퀴즈 진행 결과를 저장하고 가져오는 API")
public interface QuizResultApiController {

    @Operation(summary = "퀴즈 진행 결과를 저장하는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "퀴즈 결과를 정상적으로 저장했습니다."),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다"),
            @ApiResponse(responseCode = "403", description = "AuthorizationHeader에 문제가 있습니다."),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    ResponseEntity<ResultDto<String>> saveQuizResultV1(
            @Parameter(description = "퀴즈가 끝난 뒤 퀴즈 결과가 담긴 DTO 객체", required = true)
            @RequestParam("QuizResult") CreateQuizResultRequest request,
            @Parameter(description = "HTTP Header에서 JWT 토큰을 꺼내기 위한 변수", required = false)
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;

    @Operation(summary = "퀴즈 결과를 불러오는 API")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "퀴즈 결과를 정상적으로 가져왔습니다."),
            @ApiResponse(responseCode = "404", description = "해당 리소스를 찾을 수 없습니다"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    ResponseEntity<ResultDto<QuizResultDto>> findQuizResultByRoomCodeV1(
            @Parameter(description = "퀴즈방 번호", required = true, example = "324156")
            @PathVariable("roodCode") String roomCode);
}
