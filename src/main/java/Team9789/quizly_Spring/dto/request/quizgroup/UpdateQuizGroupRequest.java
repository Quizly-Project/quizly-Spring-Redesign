package Team9789.quizly_Spring.dto.request.quizgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name="UpdateQuizGroupRequest", description = "수정된 퀴즈 정보를 전달하는 DTO 객체")
public class UpdateQuizGroupRequest {
    @Schema(name="quizGroupId", description = "퀴즈 그룹을 식별하는 식별자")
    private Long quizGroupId;
    @Schema(name="quizTitle", description = "수정된 퀴즈 그룹 제목")
    private String quizTitle;
    @Schema(name="quizGroupDescription", description = "수정된 퀴즈 그룹 설명")
    private String quizGroupDescription;
    @Schema(name="quizzes", description = "수정된 퀴즈 목록")
    private List<UpdateQuizRequest> quizzes = new ArrayList<>();

}
