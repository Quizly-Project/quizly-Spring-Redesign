package Team9789.quizly_Spring.entity;

public enum QuizType {
    OX_QUIZ("OX_퀴즈"),
    SHORT_ANSWER_QUESTION("주관식 퀴즈"),
    ;

    private String description;

    QuizType(String description) {
        this.description = description;
    }
}
