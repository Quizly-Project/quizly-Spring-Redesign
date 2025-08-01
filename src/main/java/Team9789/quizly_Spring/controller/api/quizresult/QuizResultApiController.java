package Team9789.quizly_Spring.controller.api.quizresult;

import Team9789.quizly_Spring.dto.quiz.QuizResultDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public interface QuizResultApiController {

    ResponseEntity<ResultDto<String>> saveQuizResultV1(@RequestParam("QuizResult")CreateQuizResultRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws AuthenticationException;

    ResponseEntity<ResultDto<QuizResultDto>> findQuizResultByRoomCodeV1(@PathVariable("roodCode") String roomCode);
}
