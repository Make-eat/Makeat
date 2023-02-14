package pangyo.makeat.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RecordedFood {

    @Id
    @GeneratedValue
    @Column(name = "recorded_food_id")
    private String recordedFoodId;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food foodId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private DietRecord recordId;
}
