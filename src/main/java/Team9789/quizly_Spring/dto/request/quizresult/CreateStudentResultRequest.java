package Team9789.quizly_Spring.dto.request.quizresult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name="CreateStudentResultRequest", description = "학생들의 퀴즈 풀이 정보가 담긴 DTO 객체")
public class CreateStudentResultRequest {
    @Schema(name="nickname", description = "퀴즈를 푼 학생 이름")
    private String nickname;
    @Schema(name="selectOption", description = "학생이 선택한 답안 JSON 형식")
    private String selectOption;
    @Schema(name="result", description = "학생의 각 문제에 대한 정답 여부 JSON 형식")
    private String result;
    @Schema(name="totalScore", description = "퀴즈에서 획득한 점수")
    private Integer totalScore;
}
