package Team9789.quizly_Spring.dto;

import Team9789.quizly_Spring.entity.QuizOption;
import lombok.Data;

// 퀴즈 묶음을 저장할 때 필요한 Option 정보를 담는 DTO;
@Data
public class QuizOptionDto {

    private String optionText;
    private Integer optionNum;

    public QuizOptionDto(QuizOption quizOption) {
        this.optionText = quizOption.getOptionText();
        this.optionNum = quizOption.getOptionNum();
    }
}
