package Team9789.quizly_Spring.exception;

public class NotFoundQuizGroupException extends RuntimeException{

    public NotFoundQuizGroupException() {
        super("퀴즈 그룹을 찾을 수 없습니다.");
    }
}
