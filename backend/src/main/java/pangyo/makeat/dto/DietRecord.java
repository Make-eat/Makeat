package pangyo.makeat.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Setter
@Getter
public class DietRecord {
    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private String recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "total_id")
    private NutrientTotal totalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nut_id")
    private Nutrient nutId;

    private String date;

    private String createdAt;

    private String updatedAt;

    private String comment; // 한줄평

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "analyzed_img_url")
    private String analyzedImgUrl;
}
