package Team9789.quizly_Spring.exception;

public class ResourceOwnerMismatchException extends RuntimeException{
    public ResourceOwnerMismatchException(String message) {
        super(message);
    }
}
