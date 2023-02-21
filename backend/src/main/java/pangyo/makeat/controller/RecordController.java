package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.dto.DietRecord;
import pangyo.makeat.dto.Users;
import pangyo.makeat.service.DietRecordService;
import pangyo.makeat.service.UsersService;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    DietRecordService dietRecordService;
    UsersService usersService;

    /**
     * 식단 기록 저장
     */
    @PostMapping("/save")
    public void getRecordSave(
            @RequestParam("kakaoId") String kakaoId,
            @RequestParam("date") String date,
            @RequestParam("createdAt") String createdAt,
            @RequestParam("updatedAt") String updatedAt,
            @RequestParam("comment") String comment
            ) {
        Optional<Users> users = usersService.
//        Optional<DietRecord> dietRecord = dietRecordService.saveDietRecord();


        dietRecordService.saveDietRecord(date, createdAt, updatedAt, comment);
    }


}
