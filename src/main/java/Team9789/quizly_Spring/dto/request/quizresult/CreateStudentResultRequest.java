package Team9789.quizly_Spring.dto.request.quizresult;

import lombok.Data;

@Data
public class CreateStudentResultRequest {
    private String nickname;
    private String selectOption;
    private String result;
    private Integer totalScore;
}
