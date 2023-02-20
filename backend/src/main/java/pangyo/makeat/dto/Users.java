package pangyo.makeat.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter // setter는 리팩토링 하면서 빼야함
public class Users {
    @Id @GeneratedValue
    @Column(name="user_id")
    private Long userId;
    @Column(name="kakao_id")
    private String kakaoId;
    @Column(name="user_token")
    private String userToken;
}
