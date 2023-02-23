package pangyo.makeat.service;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pangyo.makeat.domain.ResponseDietRecord;
import pangyo.makeat.dto.*;
import pangyo.makeat.repository.*;

import java.util.List;

@Service
public class DietRecordService {

    @Autowired
    DietRecordRepository dietRecordRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AnalyzeRepository analyzeRepository;

    @Autowired
    NutrientRepository nutrientRepository;

    @Autowired
    AnalyzedFoodRepository analyzedFoodRepository;

    @Autowired
    NutrientTotalRepository nutrientTotalRepository;

    @Transactional
    public void saveDietRecord(String kakaoId, String date, String createdAt, String updatedAt, String comment, String analyzedDataId) {
        Users users = usersRepository.findByKakaoId(kakaoId).get();
        AnalyzedData analyzedData = analyzeRepository.findById(analyzedDataId).get();
        AnalyzedFood analyzedFood = analyzedFoodRepository.findById(analyzedDataId).get();
        DietRecord dietRecord = new DietRecord();

        dietRecord.setUsers(users);

        // analyzedDataId를 이용해 analyzedFood을 찾아서 nutrient 계산
        dietRecord.setNutrient(claculateNutrient(analyzedFood));

        // date 를 비교해서 NutrientTotal 테이블 조회.
        // 바로 위에서 저장한 nutrient 테이블을 TotalNutrient 값에 더함.
        dietRecord.setNutrientTotal(calculateNutrientTotal(date, dietRecord.getNutrient(), users));

        dietRecord.setAnalyzedData(analyzedData);
        dietRecord.setDate(date);
        dietRecord.setCreatedAt(createdAt);
        dietRecord.setUpdatedAt(updatedAt);
        dietRecord.setComment(comment);

        dietRecordRepository.save(dietRecord);
    }

    @Transactional
    public void putDietRecord(String recordId, String date, String createdAt, String updatedAt, String comment, String analyzedDataId) {
//        DietRecord dietRecord = dietRecordRepository.findById(recordId).get();
//        AnalyzedData analyzedData = analyzeRepository.findById(analyzedDataId).get();
//        AnalyzedFood analyzedFood = analyzedFoodRepository.findById(analyzedDataId).get();
//        DietRecord dietRecord = new DietRecord();
//
//        dietRecord.setUsers(users);
//
//        // analyzedDataId를 이용해 analyzedFood을 찾아서 nutrient 계산
//        dietRecord.setNutrient(claculateNutrient(analyzedFood));
//
//        // date 를 비교해서 NutrientTotal 테이블 조회.
//        // 바로 위에서 저장한 nutrient 테이블을 TotalNutrient 값에 더함.
//        dietRecord.setNutrientTotal(calculateNutrientTotal(date, dietRecord.getNutrient()));
//
//        dietRecord.setAnalyzedData(analyzedData);
//        dietRecord.setDate(date);
//        dietRecord.setCreatedAt(createdAt);
//        dietRecord.setUpdatedAt(updatedAt);
//        dietRecord.setComment(comment);
//
//        dietRecordRepository.save(dietRecord);
    }


    @Transactional
    public Nutrient claculateNutrient(AnalyzedFood analyzedFood) {
        Nutrient nutrient = new Nutrient();

        nutrient.setTan(analyzedFood.getFoodTan());
        nutrient.setDan(analyzedFood.getFoodDan());
        nutrient.setGi(analyzedFood.getFoodGi());
        nutrient.setNa(analyzedFood.getFoodNa());
        nutrient.setCal(analyzedFood.getFoodCal());

        // 여기서 Nutrient 테이블에 값 저장하고 반환.
        nutrientRepository.save(nutrient);

        return nutrient;
    }

    /**
     * 영양분 통계치를 계산하고 NutrientTotal 테이블에 저장.
     * @param date
     * @param nutrient
     * @param users
     * @return NutrientTotal
     */
    @Transactional
    public NutrientTotal calculateNutrientTotal(String date, Nutrient nutrient, Users users) {
        NutrientTotal nutrientTotal = nutrientTotalRepository.findNutrientTotalByDate(date);

        if (nutrientTotal == null) {    // 테이블이 없는 경우
            nutrientTotal = new NutrientTotal();
            nutrientTotal.setTotalTan(nutrient.getTan());
            nutrientTotal.setTotalDan(nutrient.getDan());
            nutrientTotal.setTotalGi(nutrient.getGi());
            nutrientTotal.setTotalNa(nutrient.getNa());
            nutrientTotal.setTotalCal(nutrient.getCal());
            nutrientTotal.setUsers(users);
        }else{  // 테이블이 있는 경우
            nutrientTotal.setTotalTan(nutrient.getTan() + nutrientTotal.getTotalTan());
            nutrientTotal.setTotalDan(nutrient.getDan() + nutrientTotal.getTotalDan());
            nutrientTotal.setTotalGi(nutrient.getGi() + nutrientTotal.getTotalGi());
            nutrientTotal.setTotalNa(nutrient.getNa() + nutrientTotal.getTotalNa());
            nutrientTotal.setTotalCal(nutrient.getCal() + nutrientTotal.getTotalCal());
            nutrientTotal.setUsers(users);
        }

        nutrientTotalRepository.save(nutrientTotal);
        return nutrientTotal;
    }

    public void deleteDietRecord(Long recordId) {
        DietRecord dietRecord = dietRecordRepository.findById(String.valueOf(recordId)).get();
        dietRecordRepository.delete(dietRecord);
    }

//    public List<ResponseDietRecord> getDietRecordList(String kakaoId, String date) {
//        List<ResponseDietRecord> responseDietRecordList = new JSONArray();
//
//    }
}
