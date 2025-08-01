package Team9789.quizly_Spring.controller;

import Team9789.quizly_Spring.dto.user.JoinDTO;
import Team9789.quizly_Spring.exception.UserExistException;
import Team9789.quizly_Spring.service.login.JoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "JoinController", description = "사용자 회원가입을 위한 컨트롤러")
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @Operation(summary = "사용자 회원가입 API", description = "사용자가 정보를 입력하고 해당 API 요청을 보내면 회원가입이 진행됨")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 회원가입했습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 사용자입니다. ")
    })
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinDTO joinDTO) throws UserExistException {

        joinService.joinProcess(joinDTO);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
