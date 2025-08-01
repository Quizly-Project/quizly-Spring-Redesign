package Team9789.quizly_Spring.controller.api.quizresult;

import Team9789.quizly_Spring.dto.quiz.QuizResultDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.exception.InvalidAuthorizationHeaderException;
import Team9789.quizly_Spring.jwt.JwtProvider;
import Team9789.quizly_Spring.service.quizresult.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
public class QuizResultApiControllerImpl implements QuizResultApiController {

    private final QuizResultService quizResultService;
    private final JwtProvider jwtProvider;

    @Override
    @PostMapping("/v1/quizResults")
    public ResponseEntity<ResultDto<String>> saveQuizResultV1(
            CreateQuizResultRequest request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
    ) throws InvalidAuthorizationHeaderException {
        int userId = getUserIdFrom(authorizationHeader);

        String roomCode = quizResultService.saveQuizResult(userId, request);
        return new ResponseEntity<>(new ResultDto<>("퀴즈 결과 저장", roomCode), HttpStatus.OK);
    }

    @Override
    @GetMapping("/v1/quizResults/{roomCode}")
    public ResponseEntity<ResultDto<QuizResultDto>> findQuizResultByRoomCodeV1(String roomCode) {
        QuizResultDto quizResultDto = quizResultService.getQuizResult(roomCode);
        return new ResponseEntity<>(new ResultDto<>("퀴즈 결과 조회", quizResultDto), HttpStatus.OK);
    }

    private int getUserIdFrom(String authorizationHeader) throws InvalidAuthorizationHeaderException{
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidAuthorizationHeaderException("authorizationHeader가 적절하지 않습니다.");
        }
        String accessToken = authorizationHeader.substring(7);
        return Integer.parseInt(jwtProvider.getUserId(accessToken));
    }

}
