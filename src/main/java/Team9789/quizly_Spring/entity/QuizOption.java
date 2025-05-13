package Team9789.quizly_Spring.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(name = "option_text")
    private String optionText;

    @Column(name = "option_num")
    private Integer optionNum;

    protected QuizOption(String optionText, Integer optionNum) {
        this.optionText = optionText;
        this.optionNum = optionNum;
    }

    //== 변경 메서드 ==//
    public void changeQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    protected void changeOptionText(String optionText) {
        this.optionText = optionText;
    }

    protected void changeOptionNum(Integer optionNum) {
        this.optionNum = optionNum;
    }

    //== 생성 메서드 ==//
    // TODO: quiz null인 상태
    public static QuizOption createQuizOption(String optionText, Integer optionNum) {
        return new QuizOption(optionText, optionNum);

    }

    //== 수정 메서드 ==//
    public void updateQuizOption(String optionText, Integer optionNum) {
        this.changeOptionText(optionText);
        this.changeOptionNum(optionNum);
    }
}
