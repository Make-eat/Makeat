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
public class Food {
    @Id
    @GeneratedValue
    @Column(name = "food_id")
    private String foodId;

    @Column(name = "food_name")
    private String foodName;
}
