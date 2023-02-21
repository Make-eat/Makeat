package pangyo.makeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pangyo.makeat.dto.DietRecord;
import pangyo.makeat.repository.DietRecordRepository;

@Service
public class DietRecordService {
//
//    @Autowired
//    DietRecordRepository dietRecordRepository;
//
//    @Transactional
//    public void saveDietRecord(String date, String createdAt, String updatedAt, String comment,
//                               String imgUrl, String analyzedImgUrl) {
//        DietRecord dietRecord = new DietRecord();
//        dietRecord.setDate(date);
//        dietRecord.setCreatedAt(createdAt);
//        dietRecord.setUpdatedAt(updatedAt);
//        dietRecord.setComment(comment);
//        dietRecord.setImgUrl(imgUrl);
//        dietRecord.setAnalyzedImgUrl(analyzedImgUrl);
//
//        dietRecordRepository.save(dietRecord);
//    }

}
