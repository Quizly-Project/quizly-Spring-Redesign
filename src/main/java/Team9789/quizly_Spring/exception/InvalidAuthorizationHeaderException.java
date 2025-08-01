package Team9789.quizly_Spring.exception;

public class InvalidAuthorizationHeaderException extends RuntimeException{
    public InvalidAuthorizationHeaderException(String message) {
        super(message);
    }
}
