package pangyo.makeat.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // 테이블
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue
    @Column(name = "info_id")
    private String infoId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userId;
    private int age;
    @Enumerated(EnumType.STRING)
    private InfoGender gender; // M, W
    private int height;
    private int weight;
    private float bmi;

}
