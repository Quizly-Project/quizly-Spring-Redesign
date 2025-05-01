package Team9789.quizly_Spring.exception;

public class NotFindQuizGroupException extends RuntimeException{

    public NotFindQuizGroupException() {
        super("퀴즈 그룹을 찾을 수 없습니다.");
    }
}
