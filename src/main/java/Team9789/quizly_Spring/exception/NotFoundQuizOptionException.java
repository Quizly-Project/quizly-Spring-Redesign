package Team9789.quizly_Spring.exception;

public class NotFoundQuizException extends RuntimeException{

    public NotFoundQuizException() {
        super("퀴즈를 찾을 수 없습니다.");
    }
}
