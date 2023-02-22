package pangyo.makeat.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    @Column(name = "total_tan")
    private float totalTan;

    @Column(name = "total_Dan")
    private float totalDan;

    @Column(name = "total_Gi")
    private float totalGi;

    @Column(name = "total_Na")
    private float totalNa;

    @Column(name = "total_cal")
    private float totalCal;

    private String date;

}
