package pangyo.makeat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nut_id")
    private Nutrient nutrient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyzed_data_id")
    private AnalyzedData analyzedData;

    private String date;

    private String time;

    @Column(name="year_month")
    private String yearMonth;

    private String comment; // 한줄평
}
