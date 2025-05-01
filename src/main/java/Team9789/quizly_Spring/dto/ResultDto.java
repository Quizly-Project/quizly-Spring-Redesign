package Team9789.quizly_Spring.dto;

import lombok.Getter;

/**
 * JSON으로 여러 데이터를 전송하기 위해 사용하는 기 래퍼 클래스
 */
@Getter
public class ResultDto<T> {
    private String message;
    private T data;


    public ResultDto(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
