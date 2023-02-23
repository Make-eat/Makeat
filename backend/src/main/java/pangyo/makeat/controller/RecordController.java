package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.requests.RequestRecord;
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
            @RequestBody RequestRecord requestRecord
            ) {
        String date = requestRecord.getDate();
        String createdAt = requestRecord.getCreatedAt();
        String updatedAt = requestRecord.getUpdatedAt();
        String comment = requestRecord.getComment();
        Long analyzedDataId = requestRecord.getAnalyzedDataId();

        dietRecordService.saveDietRecord(kakaoId, date, createdAt, updatedAt, comment, String.valueOf(analyzedDataId));
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
            @RequestBody RequestRecord requestRecord
    ) {
        String date = requestRecord.getDate();
        String createdAt = requestRecord.getCreatedAt();
        String updatedAt = requestRecord.getUpdatedAt();
        String comment = requestRecord.getComment();
        Long analyzedDataId = requestRecord.getAnalyzedDataId();

        dietRecordService.putDietRecord(recordId, date, createdAt, updatedAt, comment, String.valueOf(analyzedDataId));
    }

    /**
     * 개별 record 기록 삭제
     */
    @DeleteMapping("/{recordId}")
    public void deleteRecord(@PathVariable Long recordId) {
        dietRecordService.deleteDietRecord(recordId);
    }
}
