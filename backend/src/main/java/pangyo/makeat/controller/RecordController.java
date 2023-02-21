package pangyo.makeat.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.dto.AnalyzedData;
import pangyo.makeat.dto.DietRecord;
import pangyo.makeat.service.DietRecordService;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    DietRecordService drs;


    /**
     * 이미지 검증 - 이미지가 들어오면 먼저 이미지 데이터를 분석한 후
     * 다시 프론트로 보냄
     */
//    @ResponseBody
//    @GetMapping("/{kakaoId}/image")
//    public AnalyzedData getRecordImage(@PathVariable String kakaoId,
//                                       @RequestParam("imgUrl") String imgUrl,
//                                       HttpServletResponse response) throws IOException {
//        log.info("mappingPath kakaoId = {}", kakaoId);
//        Long userId = 123L;
//
//        /**
//         * 이미지 분석하는 로직 들어가야함.
//         */
////        drs.analyzeImage(Long userId, );
//
//    }

    /**
     * 식단 기록 저장
     */
//    @PostMapping("/save")
//    public void getRecordSave(HttpServletRequest request, HttpServletResponse response) {
//        String kakaoId = request.getParameter("kakaoId");
//
//        // totalId, userId, nutId 넣어야함
//
//        String date = request.getParameter("date");
//        String createdAt = request.getParameter("createdAt");
//        String updatedAt = request.getParameter("updatedAt");
//        String comment = request.getParameter("comment");
//        String imgUrl = request.getParameter("imgUrl");
//        String analyzedImgUrl = request.getParameter("analyzedImgUrl");
//
//        drs.saveDietRecord(date, createdAt, updatedAt, comment, imgUrl, analyzedImgUrl);
//    }


}
