package pangyo.makeat.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AnalyzedFood {
    @Id
    @GeneratedValue
    @Column(name = "analyzed_food_id")
    private Long analyzedFoodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyzed_data_id")
    private AnalyzedData analyzedData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}