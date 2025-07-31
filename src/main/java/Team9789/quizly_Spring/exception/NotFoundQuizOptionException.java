package Team9789.quizly_Spring.exception;

public class NotFoundQuizOptionException extends RuntimeException{

    public NotFoundQuizOptionException() {
        super("퀴즈 옵션을 찾을 수 없습니다.");
    }
}
