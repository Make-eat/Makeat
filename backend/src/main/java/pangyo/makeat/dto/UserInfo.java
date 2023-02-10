package pangyo.makeat.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
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
    private Integer age;
    private String gender;
    private Integer height;
    private Integer weight;
    private String bmi;

}
