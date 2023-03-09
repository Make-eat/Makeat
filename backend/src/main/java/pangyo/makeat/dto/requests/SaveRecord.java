package pangyo.makeat.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SaveRecord {
    private String date;

    private String time;

    private String comment;

    private String imgUrl;

    private String analyzedImgUrl;

    private float carb;

    private float protein;

    private float fat;

    private float na;

    private float kcal;
}
