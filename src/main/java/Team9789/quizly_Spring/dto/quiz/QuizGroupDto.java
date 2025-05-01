package Team9789.quizly_Spring.dto.quiz;

import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizGroup;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuizGroupDto {

    private Long quizGroupId;
    private String userName;
    private String quizTitle;
    private String quizGroupDescription;
    private List<QuizDto> quizzes = new ArrayList<>();

    public QuizGroupDto(QuizGroup quizGroup) {
        this.quizGroupId = quizGroup.getId();
        this.userName = quizGroup.getUserEntity().getUsername(); // 페치 조인에서 가져옴
        this.quizTitle = quizGroup.getQuizTitle();
        this.quizGroupDescription = quizGroup.getQuizGroupDescription();
        this.quizzes = quizGroup.getQuizzes().stream()
                .map(quiz -> new QuizDto(quiz))
                .collect(Collectors.toList());
    }

    public QuizGroupDto(Long quizGroupId, String userName, String quizTitle, String quizGroupDescription, List<Quiz> quizzes) {
        this.quizGroupId = quizGroupId;
        this.userName = userName;
        this.quizTitle = quizTitle;
        this.quizGroupDescription = quizGroupDescription;
//        this.quizType = quizType;
        this.quizzes = quizzes.stream()
                .map(quiz -> new QuizDto(quiz))
                .collect(Collectors.toList());
    }
}