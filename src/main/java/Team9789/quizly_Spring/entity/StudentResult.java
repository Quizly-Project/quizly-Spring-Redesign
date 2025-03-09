package Team9789.quizly_Spring.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "student_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentResult {

    @Id
    @GeneratedValue
    @Column(name = "stu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_result_id")
    private QuizResult quizResult;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "select_option")
    private String selectOption;

    @Column(name = "result")
    private String result;

    @Column(name = "total_score")
    private Integer totalScore;

    protected StudentResult(String nickname, String selectOption, String result, Integer totalScore) {
        this.nickname = nickname;
        this.selectOption = selectOption;
        this.result = result;
        this.totalScore = totalScore;
    }

    //== 연관 관계 편의 메서드 ==//
    public void changeQuizResult(QuizResult quizResult) {
        this.quizResult = quizResult;
    }

    //== 생성 메서드 ==//
    public static StudentResult createStudentResult(String nickname, String selectOption, String result, Integer totalScore) {
        return new StudentResult(nickname, selectOption, result, totalScore);

    }


}
