package pangyo.makeat.dto.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pangyo.makeat.entity.Nutrient;

@Data
public class ResponseDietRecord {
    private Long recordId;

    private String date;

    private String time;

    private String comment;

    private String imgUrl;

    private String analyzedImgUrl;

    private Nutrient nutrient;
}
