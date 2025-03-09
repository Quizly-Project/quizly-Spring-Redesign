package Team9789.quizly_Spring.dto.request.quizgroup;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateQuizRequest {
    private Long quizId;
    private String question;
    private String correctAnswer;
    private String explanation;
    private Integer quizScore;
    private List<UpdateOptionRequest> quizOptions = new ArrayList<>();
}
