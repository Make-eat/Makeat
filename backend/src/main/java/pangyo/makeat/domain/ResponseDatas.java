package pangyo.makeat.domain;

import java.util.List;

public class ResponseDatas {
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAnalyzedImgUrl() {
        return analyzedImgUrl;
    }

    public void setAnalyzedImgUrl(String analyzedImgUrl) {
        this.analyzedImgUrl = analyzedImgUrl;
    }

    public List<String> getRecordedFoodList() {
        return recordedFoodList;
    }

    public void setRecordedFoodList(List<String> recordedFoodList) {
        this.recordedFoodList = recordedFoodList;
    }

    private String imgUrl;
    private String analyzedImgUrl;
    private List<String> recordedFoodList;
}
