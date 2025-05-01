package Team9789.quizly_Spring.dto.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateRefreshToken {
    private String userId;
    private String opaqueToken;
    private String uuid;

    public static CreateRefreshToken create(String userId, String opaqueToken, String uuid) {
        return CreateRefreshToken.builder()
                .userId(userId)
                .opaqueToken(opaqueToken)
                .uuid(uuid)
                .build();
    }
}
