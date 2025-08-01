package Team9789.quizly_Spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * JSON으로 여러 데이터를 전송하기 위해 사용하는 기 래퍼 클래스
 */
@Getter
@Schema(name="ResultDto", description = "사용자에게 동일한 응답을 제공하기 위한 DTO 객체")
public class ResultDto<T> {
    @Schema(name="message", description = "요청에 대한 서버에서 제공하는 간단한 message")
    private String message;
    @Schema(name="data", description = "요청에 대한 응답 데이터")
    private T data;


    public ResultDto(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
