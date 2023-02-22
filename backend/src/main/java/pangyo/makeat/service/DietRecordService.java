package pangyo.makeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pangyo.makeat.dto.AnalyzedData;
import pangyo.makeat.dto.DietRecord;
import pangyo.makeat.dto.Users;
import pangyo.makeat.repository.AnalyzeRepository;
import pangyo.makeat.repository.DietRecordRepository;
import pangyo.makeat.repository.UsersRepository;

@Service
public class DietRecordService {

    @Autowired
    DietRecordRepository dietRecordRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AnalyzeRepository analyzeRepository;


    @Transactional
    public void saveDietRecord(String kakaoId, String date, String createdAt, String updatedAt, String comment, String analyzedDataId) {
        Users users = usersRepository.findByKakaoId(kakaoId).get();
        AnalyzedData analyzedData = analyzeRepository.findById(analyzedDataId).get();
        DietRecord dietRecord = new DietRecord();

        // Date 를 파싱해서 totalId 계산


        // analyzedDate 에서 nutId 계산

        dietRecord.setUsers(users);
        dietRecord.setAnalyzedData(analyzedData);
        dietRecord.setDate(date);
        dietRecord.setCreatedAt(createdAt);
        dietRecord.setUpdatedAt(updatedAt);
        dietRecord.setComment(comment);

        dietRecordRepository.save(dietRecord);
    }

}
