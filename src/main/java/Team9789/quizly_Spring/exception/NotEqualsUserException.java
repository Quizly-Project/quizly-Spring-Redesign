package Team9789.quizly_Spring.exception;

public class NotEqualsUserException extends RuntimeException{
    public NotEqualsUserException() {
        super();
    }

    public NotEqualsUserException(String message) {
        super(message);
    }

    public NotEqualsUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEqualsUserException(Throwable cause) {
        super(cause);
    }

    protected NotEqualsUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
