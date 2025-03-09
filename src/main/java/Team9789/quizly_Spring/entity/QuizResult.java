package Team9789.quizly_Spring.entity;

import Team9789.quizly_Spring.dto.request.quizresult.CreateStudentResultRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@BatchSize(size = 100)
@Entity
@Table(name = "quiz_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizResult {

    @Id
    @GeneratedValue
    @Column(name = "quiz_result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_group_id")
    private QuizGroup quizGroup;

    @Column(name = "room_code")
    private String roomCode;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "quizResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentResult> studentResults = new ArrayList<>();


    protected QuizResult(UserEntity userEntity, QuizGroup quizGroup, String roomCode) {
        this.userEntity = userEntity;
        this.quizGroup = quizGroup;
        this.roomCode = roomCode;
        this.regDate = LocalDateTime.now();
    }


    //== 연관관계 편의 메서드 ==//
    public void addStudentResult(StudentResult studentResult) {
        this.getStudentResults().add(studentResult);
        studentResult.changeQuizResult(this);
    }


    //== 생성 메서드 ==//
    public static QuizResult createQuizResult(UserEntity userEntity, QuizGroup quizGroup, String roomCode, List<CreateStudentResultRequest> studentResults) {
        QuizResult quizResult = new QuizResult(userEntity, quizGroup, roomCode);

        studentResults.forEach(
                studentResult ->
                        quizResult.addStudentResult(
                                StudentResult.createStudentResult(
                                        studentResult.getNickname(),
                                        studentResult.getSelectOption(),
                                        studentResult.getResult(),
                                        studentResult.getTotalScore()
                                )
                        )
        );
        return quizResult;
    }
}
