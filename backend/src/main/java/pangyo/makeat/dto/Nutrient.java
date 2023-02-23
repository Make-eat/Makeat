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
public class Nutrient {
    @Id @GeneratedValue
    @Column(name = "nut_id")
    private Long nutId;

    private float tan;

    private float dan;

    private float gi;

    private float na;

    private float cal;
}
