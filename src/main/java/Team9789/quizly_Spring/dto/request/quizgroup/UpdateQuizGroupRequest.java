package Team9789.quizly_Spring.dto.request.quizgroup;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateQuizGroupRequest {
    private Long quizGroupId;
    private String quizTitle;
    private String quizGroupDescription;
    private List<UpdateQuizRequest> quizzes = new ArrayList<>();

}
