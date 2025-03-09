package Team9789.quizly_Spring.dto;

import Team9789.quizly_Spring.entity.QuizResult;
import lombok.Data;
import org.hibernate.id.IntegralDataTypeHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuizResultDto {
    private Integer userId;
    private String username;
    private Long quizGroupId;
    private String roomCode;
    private LocalDateTime regDate;
    private List<StudentResultDto> studentResults = new ArrayList<>();

    public QuizResultDto(QuizResult quizResult) {
        this.userId = quizResult.getUserEntity().getId();
        this.username = quizResult.getUserEntity().getUsername();
        this.quizGroupId = quizResult.getQuizGroup().getId();
        this.roomCode = quizResult.getRoomCode();
        this.regDate = quizResult.getRegDate();
        this.studentResults = quizResult.getStudentResults().stream()
                .map(studentResult -> new StudentResultDto(studentResult))
                .collect(Collectors.toList());
    }
}
