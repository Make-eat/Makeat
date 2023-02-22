package pangyo.makeat.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDatas {
//    public Long getAnalyzedDataId() { return analyzedDataId; }
//
//    public void setAnalyzedDataId(Long analyzedDataId){ this.analyzedDataId = analyzedDataId; }
//
//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
//
//    public String getAnalyzedImgUrl() {
//        return analyzedImgUrl;
//    }
//
//    public void setAnalyzedImgUrl(String analyzedImgUrl) {
//        this.analyzedImgUrl = analyzedImgUrl;
//    }
//
//    public List<String> getRecordedFoodList() {
//        return recordedFoodList;
//    }
//
//    public void setRecordedFoodList(List<String> recordedFoodList) {
//        this.recordedFoodList = recordedFoodList;
//    }

    private Long analyzedDataId;
    private String imgUrl;
    private String analyzedImgUrl;
    private List<String> recordedFoodList;
}
