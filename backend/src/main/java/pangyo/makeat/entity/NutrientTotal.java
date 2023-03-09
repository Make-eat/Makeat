package pangyo.makeat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NutrientTotal {
    @Id
    @GeneratedValue
    @Column(name = "total_id")
    private Long totalId;

    @Column(name = "total_carb")
    private float totalCarb;

    @Column(name = "total_protein")
    private float totalProtein;

    @Column(name = "total_fat")
    private float totalFat;

    @Column(name = "total_Na")
    private float totalNa;

    @Column(name = "total_kcal")
    private float totalKcal;

    private String date;

    @Column(name="year_month")
    private String yearMonth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
