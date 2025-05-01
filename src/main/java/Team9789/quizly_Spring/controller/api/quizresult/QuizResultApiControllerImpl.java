package Team9789.quizly_Spring.controller.api.quizresult;

import Team9789.quizly_Spring.dto.QuizResultDto;
import Team9789.quizly_Spring.dto.ResultDto;
import Team9789.quizly_Spring.dto.UserDto;
import Team9789.quizly_Spring.dto.request.quizresult.CreateQuizResultRequest;
import Team9789.quizly_Spring.service.quizresult.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuizResultApiControllerImpl implements QuizResultApiController {

    private final QuizResultService quizResultService;

    /**
     * 퀴즈 결과 저장
     */
    @Override
    @PostMapping("/api/v1/quizResults")
    public ResponseEntity<ResultDto<String>> saveQuizResultV1(CreateQuizResultRequest request) {
        // TODO: JWT에서 값을 꺼내오는 작업 해야 함
        // 임시로 데이터 사용
        UserDto userDto = createTemporaryUserEntity();
        String roomCode = quizResultService.saveQuizResult(userDto, request);
        return new ResponseEntity<ResultDto<String>>(new ResultDto<>("퀴즈 결과 저장", roomCode), HttpStatus.OK);
    }

    /**
     * 퀴즈 결과 가져오기
     */
    @Override
    @GetMapping("/api/v1/quizResults/{roomCode}")
    public ResponseEntity<ResultDto<QuizResultDto>> findQuizResultByRoomCodeV1(String roomCode) {
        QuizResultDto quizResultDto = quizResultService.getQuizResult(roomCode);
        return new ResponseEntity<ResultDto<QuizResultDto>>(new ResultDto<>("퀴즈 결과 조회", quizResultDto), HttpStatus.OK);
    }

    // 임시 유저 데이터 사용
    private UserDto createTemporaryUserEntity() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUsername("홍길동");
        return userDto;
    }
}
