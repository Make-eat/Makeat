package pangyo.makeat.dto;

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
    private Users users;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "analyzed_img_url")
    private String analyzedImgUrl;
}
