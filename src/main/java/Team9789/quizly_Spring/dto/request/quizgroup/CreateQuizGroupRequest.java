package Team9789.quizly_Spring.dto.request.quizgroup;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateQuizGroupRequest {

    private String quizTitle;
    private String quizGroupDescription;
    private List<CreateQuizRequest> quizzes = new ArrayList<>();

}
