package pangyo.makeat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AnalyzedData {
    @Id
    @GeneratedValue
    @Column(name = "analyzed_data_id")
    private Long analyzedDateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "analyzed_img_url")
    private String analyzedImgUrl;
}
