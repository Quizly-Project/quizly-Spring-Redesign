package Team9789.quizly_Spring.dto.request.quizresult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name="CreateQuizResultRequest", description = "퀴즈 결과 정보를 전달하는 DTO 객체")
public class CreateQuizResultRequest {
    @Schema(name="quizGroupId", description = "진행한 퀴즈 그룹 식별자")
    private Long quizGroupId;
    @Schema(name="roomCode", description = "진행한 퀴즈의 퀴즈룸 방코드")
    private String roomCode;
    @Schema(name="studentResults", description = "학생들의 퀴즈 풀이 정보 목록")
    private List<CreateStudentResultRequest> studentResults = new ArrayList<>();
}
