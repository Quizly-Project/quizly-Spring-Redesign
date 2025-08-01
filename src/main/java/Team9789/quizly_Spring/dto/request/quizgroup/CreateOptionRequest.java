package Team9789.quizly_Spring.dto.request.quizgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name="CreateOptionRequest", description = "생성된 퀴즈 선택지를 전달하는 DTO 객체")
public class CreateOptionRequest {
    @Schema(name="optionText", description = "퀴즈 선택지 설명란")
    private String optionText;
    @Schema(name="optionNum", description = "퀴즈 선택지에 대응되는 값")
    private Integer optionNum;
}
