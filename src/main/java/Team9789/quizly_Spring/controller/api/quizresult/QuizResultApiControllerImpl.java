package Team9789.quizly_Spring.controller.api.quizresult;

import Team9789.quizly_Spring.dto.quiz.QuizResultDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
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
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(new ResultDto<>("인증 실패", ""), HttpStatus.UNAUTHORIZED);
        }
        String accessToken = authorizationHeader.substring(7);
        int userId = Integer.parseInt(jwtProvider.getUserId(accessToken));

        String roomCode = quizResultService.saveQuizResult(userId, request);
        return new ResponseEntity<>(new ResultDto<>("퀴즈 결과 저장", roomCode), HttpStatus.OK);
    }

    @Override
    @GetMapping("/v1/quizResults/{roomCode}")
    public ResponseEntity<ResultDto<QuizResultDto>> findQuizResultByRoomCodeV1(String roomCode) {
        QuizResultDto quizResultDto = quizResultService.getQuizResult(roomCode);
        return new ResponseEntity<>(new ResultDto<>("퀴즈 결과 조회", quizResultDto), HttpStatus.OK);
    }

}
