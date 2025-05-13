package Team9789.quizly_Spring.entity;


import Team9789.quizly_Spring.dto.request.quizgroup.CreateQuizRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="quiz_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_group_id")
    private Long id;

    // 퀴즈 제작 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserEntity userEntity;

    // 퀴즈 묶음 제목
    @Column(name = "quiz_title")
    private String quizTitle;

    // 퀴즈 묶음 설명
    @Column(name="quiz_group_description")
    private String quizGroupDescription;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;

//    @Enumerated(EnumType.STRING)
//    private QuizType quizType;

    // 퀴즈 목록
    @OneToMany(mappedBy = "quizGroup", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();


    protected QuizGroup(UserEntity userEntity, String quizTitle, String quizGroupDescription) {
        this.userEntity = userEntity;
        this.quizTitle = quizTitle;
        this.quizGroupDescription = quizGroupDescription;
        this.regDate = LocalDateTime.now();
    }

    // 변경 메서드
    protected void changeQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    protected void changeQuizGroupDescription(String quizGroupDescription) {
        this.quizGroupDescription = quizGroupDescription;
    }

    //== 연관관계 편의 메서드 ==//
    // 퀴즈에 QuizGroup을 넣어야 한다.
    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
        quiz.changeQuizGroup(this);
    }

    //== 생성 메서드 =//
    public static QuizGroup createQuizGroup(UserEntity userEntity, String quizTitle, String quizGroupDescription, List<CreateQuizRequest> quizzes) {
        QuizGroup newQuizGroup = new QuizGroup(userEntity, quizTitle, quizGroupDescription);

        quizzes.forEach(quiz ->
                newQuizGroup.addQuiz(
                        Quiz.createQuiz(
                                quiz.getQuizType(),
                                quiz.getQuestion(),
                                quiz.getCorrectAnswer(),
                                quiz.getExplanation(),
                                quiz.getQuizScore(),
                                quiz.getOptions()
                        )
                )
        );
        return newQuizGroup;
    }

    //== 수정 메서드 ==//
    public void updateQuizGroup(String quizTitle, String quizGroupDescription) {
        this.changeQuizTitle(quizTitle);
        this.changeQuizGroupDescription(quizGroupDescription);
    }

}
