package Team9789.quizly_Spring.entity;

import Team9789.quizly_Spring.dto.request.quizgroup.CreateOptionRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id
    @GeneratedValue
    @Column(name = "quiz_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quiz_group_id")
    private QuizGroup quizGroup;

    @Enumerated(EnumType.STRING)
    private QuizType quizType;

    @Column(name = "question")
    private String question;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "quiz_score")
    private Integer quizScore;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizOption> quizOptions = new ArrayList<>();

    protected Quiz(QuizType quizType, String question, String correctAnswer, String explanation, Integer quizScore) {
        this.quizType = quizType;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.quizScore = quizScore;
        this.regDate = LocalDateTime.now();
    }

    public void changeQuizGroup(QuizGroup quizGroup) {
        this.quizGroup = quizGroup;
    }

    // 변경 메서드
    protected void changeQuestion(String question) {
        this.question = question;
    }

    protected void changeCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    protected void changeExplanation(String explanation) {
        this.explanation = explanation;
    }

    protected void changeQuizScore(Integer quizScore) {
        this.quizScore = quizScore;
    }

    protected void changeQuizOptions(List<QuizOption> quizOptions) {
        this.quizOptions = quizOptions;
    }

    // 연관관계 편의 메서드
    public void addOption(QuizOption quizOption) {
        this.quizOptions.add(quizOption);
        quizOption.changeQuiz(this);
    }

    //== 생성 메서드 ==//
    public static Quiz createQuiz(QuizType quizType, String question, String correctAnswer, String explanation, Integer quizScore, List<CreateOptionRequest> options) {
        Quiz newQuiz = new Quiz(quizType, question, correctAnswer, explanation, quizScore);

        options.forEach(option ->
                newQuiz.addOption(
                        QuizOption.createQuizOption(
                                option.getOptionText(),
                                option.getOptionNum()
                        )
                )
        );
        return newQuiz;
    }

    //== 수정 메서드 ==//
    public void updateQuiz(String question, String correctAnswer, String explanation, Integer quizScore) {
        this.changeQuestion(question);
        this.changeCorrectAnswer(correctAnswer);
        this.changeExplanation(explanation);
        this.changeQuizScore(quizScore);
    }
}
