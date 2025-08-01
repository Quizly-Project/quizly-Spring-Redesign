package Team9789.quizly_Spring.entity;

public enum UserRole {
    USER("일반유저"),
    ADMIN("관리자"),
    ;

    private final String description;

    UserRole(String description) {
        this.description = description;
    }
}
