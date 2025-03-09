package Team9789.quizly_Spring.dto.request.quizresult;

import Team9789.quizly_Spring.dto.StudentResultDto;
import Team9789.quizly_Spring.dto.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateQuizResultRequest {

    private Long quizGroupId;
    private String roomCode;
    private List<CreateStudentResultRequest> studentResults = new ArrayList<>();
}
