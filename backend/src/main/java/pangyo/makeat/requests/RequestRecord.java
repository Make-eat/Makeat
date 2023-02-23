package pangyo.makeat.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRecord {
    private String date;

    private String createdAt;

    private String updatedAt;

    private String comment;

    private Long analyzedDataId;
}
