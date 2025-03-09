package Team9789.quizly_Spring.dto.request.quizgroup;

import lombok.Data;

@Data
public class UpdateOptionRequest {
    private Long optionId;
    private String optionText;
    private Integer optionNum;
}
