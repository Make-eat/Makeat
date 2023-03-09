package pangyo.makeat.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDatas {
    private Long analyzedDataId;

    private String imgUrl;

    private String analyzedImgUrl;

    private List<String> recordedFoodList;
}
