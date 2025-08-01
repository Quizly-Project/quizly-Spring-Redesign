package Team9789.quizly_Spring.dto.request.quizgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name="CreateQuizGroupRequest", description = "생성된 퀴즈 묶음 정보를 전달하는 DTO 객체")
public class CreateQuizGroupRequest {

    @Schema(name="quizTitle", description = "퀴즈 묶음을 설명하는 퀴즈 묶음 제목")
    private String quizTitle;
    @Schema(name="quizGroupDescription", description = "퀴즈 묶음에 대한 설명")
    private String quizGroupDescription;
    @Schema(name="quizzes", description = "퀴즈 묶음의 퀴즈 목록")
    private List<CreateQuizRequest> quizzes = new ArrayList<>();

}
