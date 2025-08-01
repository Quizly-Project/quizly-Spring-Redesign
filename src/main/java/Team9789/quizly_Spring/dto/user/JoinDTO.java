package Team9789.quizly_Spring.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name="JoinDto", description = "회원가입 할 때, 사용자로부터 정보를 수신하기 위한 RequestDto 객체")
public class JoinDTO {

    @Schema(name="username", description = "사용자 ID")
    private String username;
    @Schema(name="password", description = "사용자 password")
    private String password;
    @Schema(name="email", description = "사용자 Email")
    private String email;
}
