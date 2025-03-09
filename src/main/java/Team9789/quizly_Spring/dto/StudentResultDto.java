package Team9789.quizly_Spring.dto;

import Team9789.quizly_Spring.entity.StudentResult;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentResultDto {

    private Long studentId;
    private String nickname;
    private String selectOption;
    private String result;
    private Integer totalScore;

    public StudentResultDto(StudentResult studentResult) {
        this.studentId = studentResult.getId();
        this.nickname = studentResult.getNickname();
        this.selectOption = studentResult.getSelectOption();
        this.result = studentResult.getResult();
        this.totalScore = studentResult.getTotalScore();
    }
}
