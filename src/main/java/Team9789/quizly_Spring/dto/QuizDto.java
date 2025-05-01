package Team9789.quizly_Spring.dto;

import Team9789.quizly_Spring.entity.Quiz;
import Team9789.quizly_Spring.entity.QuizOption;
import Team9789.quizly_Spring.entity.QuizType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 퀴즈 묶음을 등록할 때 필요한 퀴즈 정보를 담는 DTO
@Data
public class QuizDto {

    private Long quizId;
    private QuizType quizType;
    private String question;
    private String explanation;
    private Integer quizScore;
    private LocalDateTime regDate;
    private List<QuizOptionDto> optionDtos = new ArrayList<>();

    public QuizDto(Quiz quiz) {
        this.quizId = quiz.getId(); // batchSize로 가져온다.
        this.question = quiz.getQuestion();
        this.quizType = quiz.getQuizType();
        this.explanation = quiz.getExplanation();
        this.quizScore = quiz.getQuizScore();
        this.regDate = quiz.getRegDate();
        this.optionDtos = quiz.getQuizOptions().stream()
                .map(quizOption -> new QuizOptionDto(quizOption))
                .collect(Collectors.toList());
    }
}
