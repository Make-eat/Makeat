package pangyo.makeat.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutRecord {
    private String date;

    private String time;

    private String comment;
}
