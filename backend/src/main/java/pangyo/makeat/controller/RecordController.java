package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.requests.PutRecord;
import pangyo.makeat.requests.SaveRecord;
import pangyo.makeat.responses.ResponseDietRecord;
import pangyo.makeat.service.DietRecordService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    DietRecordService dietRecordService;

    /**
     * 식단 기록 저장
     */
    @PostMapping("/save/{kakaoId}")
    public void saveRecord(
            @PathVariable String kakaoId,
            @RequestBody SaveRecord saveRecord
            ) {
        String date = saveRecord.getDate();
        String time = saveRecord.getTime();
        String comment = saveRecord.getComment();
        String imgUrl = saveRecord.getImgUrl();
        String analyzedImgUrl = saveRecord.getAnalyzedImgUrl();
        float carbohydrate = saveRecord.getCarbohydrate();
        float protein = saveRecord.getProtein();
        float fat = saveRecord.getFat();
        float na = saveRecord.getNa();
        float kcal = saveRecord.getKcal();


        dietRecordService.saveDietRecord(
                kakaoId, date, time, comment, imgUrl, analyzedImgUrl, carbohydrate, protein, fat, na, kcal
        );
    }

    /**
     * 월별 record 기록 요청
     */
    @GetMapping("/{kakaoId}")
    public List<ResponseDietRecord> getRecordList(
            @PathVariable String kakaoId,
            @RequestParam("yearMonth") String yearMonth
    ) {
        return dietRecordService.getDietRecordList(kakaoId, yearMonth);
    }

    /**
     * 개별 record 기록 수정
     */
    @PutMapping("/{recordId}")
    public void putRecord(
            @PathVariable String recordId,
            @RequestBody PutRecord putRecord
    ) {
        String date = putRecord.getDate();
        String time = putRecord.getTime();
        String comment = putRecord.getComment();

        dietRecordService.putDietRecord(recordId, date, time, comment);
    }

    /**
     * 개별 record 기록 삭제
     */
//    @DeleteMapping("/{recordId}")
//    public void deleteRecord(@PathVariable Long recordId) {
//        dietRecordService.deleteDietRecord(recordId);
//    }
}
