package Team9789.quizly_Spring.dto.request.quizgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name="UpdateQuizRequest", description = "수정된 퀴즈의 정보를 전달하는 DTO 객체")
public class UpdateQuizRequest {
    @Schema(name="quizId", description = "수정된 퀴즈를 식별하는 식별자")
    private Long quizId;
    @Schema(name="question", description = "수정된 퀴즈 제목")
    private String question;
    @Schema(name="correctAnswer", description = "수정된 퀴즈 정답")
    private String correctAnswer;
    @Schema(name="explanation", description = "수정된 퀴즈 질문 내용")
    private String explanation;
    @Schema(name="quizScore", description = "수정된 퀴즈 점수")
    private Integer quizScore;
    @Schema(name="quizOptions", description = "수정된 퀴즈 선택지 목록")
    private List<UpdateOptionRequest> quizOptions = new ArrayList<>();
}
