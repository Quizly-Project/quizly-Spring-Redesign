package Team9789.quizly_Spring.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(name = "JoinDto", description = "회원가입 할 때, 사용자로부터 정보를 수신하기 위한 RequestDto 객체")
public class JoinDTO {

    @Schema(name = "username", description = "사용자 ID")
    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4자 이상  20자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 사용할 수 있습니다.")
    private String username;

    @Schema(name = "password", description = "사용자 password")
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 8자 이상 30자 이하여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,30}$",
            message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;

    @Schema(name = "email", description = "사용자 Email")
    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    private String email;
}
