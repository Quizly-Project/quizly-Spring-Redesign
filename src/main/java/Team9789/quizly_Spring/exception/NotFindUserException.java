package Team9789.quizly_Spring.exception;

public class NotFindUserException extends RuntimeException {
    public NotFindUserException() {
        super("유저 정보가 존재하지 않습니다.");
    }
}
