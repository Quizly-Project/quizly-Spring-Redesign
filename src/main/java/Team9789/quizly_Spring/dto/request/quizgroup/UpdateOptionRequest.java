package Team9789.quizly_Spring.dto.request.quizgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name="UpdateOptionRequest", description = "수정된 퀴즈 선택지 정보를 전달하는 DTO 객체")
public class UpdateOptionRequest {
    @Schema(name="optionId", description = "수정된 퀴즈 선택지를 식별하는 식별자")
    private Long optionId;
    @Schema(name="optionText", description = "수정된 퀴즈 선택지 설명란")
    private String optionText;
    @Schema(name="", description = "수정된 퀴즈 선택지에 대응되는 값")
    private Integer optionNum;
}
