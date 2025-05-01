package Team9789.quizly_Spring.dto.request.quizresult;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateQuizResultRequest {

    private Long quizGroupId;
    private String roomCode;
    private List<CreateStudentResultRequest> studentResults = new ArrayList<>();
}
