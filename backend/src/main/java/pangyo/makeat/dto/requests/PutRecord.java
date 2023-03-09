package pangyo.makeat.dto.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PutRecord {
    private String date;

    private String time;

    private String comment;
}
