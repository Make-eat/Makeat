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

    private String tan;

    private String dan;

    private String gi;

    private String na;

    private String cal;
}
