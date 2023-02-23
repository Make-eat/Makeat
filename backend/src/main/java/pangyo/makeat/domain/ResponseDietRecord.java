package pangyo.makeat.domain;

import lombok.Getter;
import lombok.Setter;

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

    private float tan;

    private float dan;

    private float zi;

    private float na;

    private float cal;
}
