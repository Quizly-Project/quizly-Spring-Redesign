package Team9789.quizly_Spring.exception;

public class NotFoundResourceException extends RuntimeException{
    public NotFoundResourceException(String message) {
        super(message);
    }
}
