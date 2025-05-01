package Team9789.quizly_Spring.exception;

public class NotFindQuizResultException extends RuntimeException{

    public NotFindQuizResultException() {
        super("퀴즈 결과를 찾을 수 없습니다.");
    }
}
