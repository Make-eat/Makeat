package pangyo.makeat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Nutrient {
    @Id @GeneratedValue
    @Column(name = "nut_id")
    private Long nutId;

    private float carbohydrate;

    private float protein;

    private float fat;

    private float na;

    private float kcal;
}
