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
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "total_id")
    private NutrientTotal nutrientTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nut_id")
    private Nutrient nutrient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyzed_data_id")
    private AnalyzedData analyzedData;

    private String date;

    @Column(name="year_month")
    private String yearMonth;

    private String createdAt;

    private String updatedAt;

    private String comment; // 한줄평
}
