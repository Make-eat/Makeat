package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
            @RequestParam("date") String date,
            @RequestParam("createdAt") String createdAt,
            @RequestParam("updatedAt") String updatedAt,
            @RequestParam("comment") String comment,
            @RequestParam("analyzedDataId") String analyzedDataId
            ) {

        dietRecordService.saveDietRecord(kakaoId, date, createdAt, updatedAt, comment, analyzedDataId);
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
            @RequestParam("date") String date,
            @RequestParam("createdAt") String createdAt,
            @RequestParam("updatedAt") String updatedAt,
            @RequestParam("comment") String comment,
            @RequestParam("analyzedDataId") String analyzedDataId
    ) {
        dietRecordService.putDietRecord(recordId, date, createdAt, updatedAt, comment, analyzedDataId);
    }

    /**
     * 개별 record 기록 삭제
     */
    @DeleteMapping("/{recordId}")
    public void deleteRecord(@PathVariable Long recordId) {
        dietRecordService.deleteDietRecord(recordId);
    }
}
