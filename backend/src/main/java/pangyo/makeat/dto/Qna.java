package pangyo.makeat.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Qna {
    @Id @GeneratedValue
    @Column(name="qna_id")
    private String qnaId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Users userId;

    private String context;

    private String email;
}
