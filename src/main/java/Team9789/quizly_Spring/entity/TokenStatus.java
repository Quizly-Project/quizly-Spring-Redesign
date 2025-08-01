package Team9789.quizly_Spring.entity;

public enum TokenStatus {
    VALID("유효"),
    EXPIRED("만료"),
    INVALID("무효")
    ;

    private final String description;

    TokenStatus(String description) {
        this.description = description;
    }
}