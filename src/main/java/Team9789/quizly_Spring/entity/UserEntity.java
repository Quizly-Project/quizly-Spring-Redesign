package Team9789.quizly_Spring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@BatchSize(size = 100)
public class UserEntity {

    // 유저 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 유저 이름
    private String username;

    // 비밀번호
    private String password;

    // 이메일
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    // 계정 상태 관리용 필드
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;

    public static UserEntity to(String username, String password, String email) {
        return UserEntity.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(UserRole.USER)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

    public boolean isEqualsId(int id) {
        return this.id == id;
    }
}
