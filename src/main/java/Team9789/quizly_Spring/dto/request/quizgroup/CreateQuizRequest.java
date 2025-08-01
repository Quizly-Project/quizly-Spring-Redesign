package Team9789.quizly_Spring.dto.request.quizgroup;

import Team9789.quizly_Spring.entity.QuizType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name="CreateQuizRequest", description = "생성된 퀴즈 정보를 전달하는 DTO 객체")
public class CreateQuizRequest {

    @Schema(name="quizType", description = "퀴즈 유형")
    private QuizType quizType;
    @Schema(name="question", description = "퀴즈 제목")
    private String question;
    @Schema(name="correctAnswer", description = "퀴즈 정답")
    private String correctAnswer;
    @Schema(name="explanation", description = "퀴즈 질문 내용")
    private String explanation;
    @Schema(name="quizScore", description = "퀴즈 점수")
    private Integer quizScore;
    @Schema(name="options", description = "퀴즈 선택지 목록")
    private List<CreateOptionRequest> options = new ArrayList<>();

}
