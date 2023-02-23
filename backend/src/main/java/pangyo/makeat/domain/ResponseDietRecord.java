package pangyo.makeat.domain;

import lombok.Getter;
import lombok.Setter;
import pangyo.makeat.dto.Nutrient;

@Getter
@Setter
public class ResponseDietRecord {
    private Long recordId;

    private String date;

    private String createdAt;

    private String updatedAt;

    private String comment;

    private String imgUrl;

    private String analyzedImgUrl;

    private Nutrient nutrient;
}
