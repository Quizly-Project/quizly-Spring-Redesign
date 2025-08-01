package Team9789.quizly_Spring.controller.api.quizgroup;

import Team9789.quizly_Spring.dto.quiz.QuizGroupDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizGroupRequest;
import Team9789.quizly_Spring.dto.request.quizgroup.UpdateQuizGroupRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
public interface QuizGroupApiController {

    ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupByUsernameV1(
            @PathVariable("username") String username,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    );

    @GetMapping("/api/v1/quizgroups")
    ResponseEntity<ResultDto<List<QuizGroupDto>>> findQuizGroupAllV1(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    );

    ResponseEntity<ResultDto<QuizGroupDto>> findQuizGroupOneV1(@PathVariable("quizgroupId") Long quizgroupId);

    ResponseEntity<ResultDto<Long>> addQuizGroupV1(@RequestParam("Quizgroup") CreateQuizGroupRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;

    ResponseEntity<ResultDto<Long>> updateQuizGroupV1(@RequestParam("Quizgroup") UpdateQuizGroupRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;

    ResponseEntity<ResultDto<Long>> removeQuizGroupV1(@PathVariable("quizgroupId") Long quizgroupId, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;
}
