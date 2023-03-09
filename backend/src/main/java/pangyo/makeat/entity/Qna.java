package pangyo.makeat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Qna {
    @Id @GeneratedValue
    @Column(name="qna_id")
    private Long qnaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userId;
    // private Users users; 이렇게 바꿔 주세요!

    private String context;

    private String email;
}
