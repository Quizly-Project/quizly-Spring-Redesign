package Team9789.quizly_Spring.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;

    private ExceptionResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public static ExceptionResponse of(Date timeStamp, String message, String details) {
        return new ExceptionResponse(timeStamp, message, details);
    }
}
