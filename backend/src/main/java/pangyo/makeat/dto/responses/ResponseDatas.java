package pangyo.makeat.dto.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ResponseDatas {
    private Long analyzedDataId;

    private String imgUrl;

    private String analyzedImgUrl;

    private List<String> recordedFoodList;
}
