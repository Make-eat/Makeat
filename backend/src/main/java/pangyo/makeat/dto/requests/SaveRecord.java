package pangyo.makeat.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveRecord {
    private String date;

    private String time;

    private String comment;

    private String imgUrl;

    private String analyzedImgUrl;

    private float carbohydrate;

    private float protein;

    private float fat;

    private float na;

    private float kcal;
}
