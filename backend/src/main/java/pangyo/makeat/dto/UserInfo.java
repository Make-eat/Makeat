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
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users userId;
    private int age;
    private String gender;
    private int height;
    private int weight;
    private String bmi;

}
