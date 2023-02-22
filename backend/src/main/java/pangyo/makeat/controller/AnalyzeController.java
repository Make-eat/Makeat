package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.domain.ResponseDatas;
import pangyo.makeat.dto.AnalyzedData;
import pangyo.makeat.dto.AnalyzedFood;
import pangyo.makeat.service.AnalyzeService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/analyze")
public class AnalyzeController {
    @Autowired
    AnalyzeService as;

    @PostMapping("/{kakaoId}")
    public ResponseDatas analyzeImage(@PathVariable String kakaoId, @RequestBody AnalyzedData analyzedData) {
        ResponseDatas responseDatas = new ResponseDatas();
        log.info("imgUrl = {}", analyzedData.getImgUrl());

        //딥러닝서버에서 analyzedImgUrl 받아오기
        AnalyzedData deepAnalyzedData = new AnalyzedData();
        deepAnalyzedData.setAnalyzedImgUrl("http://analyzedImg.url");

        //딥러닝서버에서 food_name 받아와서 세팅
        AnalyzedFood deepAnalyzedFood = new AnalyzedFood();
        deepAnalyzedFood.setFoodName("고구마");
        List<String> foodList = new ArrayList<>();
        foodList.add("고구마");
        foodList.add("닭가슴살");

        //AnalyzedData DB에 넣기
        AnalyzedData savedAnalyzedDate = as.saveData(kakaoId, analyzedData.getImgUrl(), deepAnalyzedData.getAnalyzedImgUrl());

        // return할 json 세팅
        responseDatas.setAnalyzedDataId(savedAnalyzedDate.getAnalyzedDateId());
        responseDatas.setRecordedFoodList(foodList);
        responseDatas.setImgUrl(analyzedData.getImgUrl());
        responseDatas.setAnalyzedImgUrl(deepAnalyzedData.getAnalyzedImgUrl());

        return responseDatas;
    }
}
