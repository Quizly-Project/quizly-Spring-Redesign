package Team9789.quizly_Spring.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("유저 정보가 존재하지 않습니다.");
    }
}
