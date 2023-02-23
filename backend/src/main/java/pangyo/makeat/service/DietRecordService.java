package pangyo.makeat.service;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pangyo.makeat.responses.ResponseDietRecord;
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
        dietRecord.setYearMonth(date.substring(0, 6));
        dietRecord.setCreatedAt(createdAt);
        dietRecord.setUpdatedAt(updatedAt);
        dietRecord.setComment(comment);

        dietRecordRepository.save(dietRecord);
    }

    /**
     * 개별 record 수정. 이미지는 변경 불가. 날짜와 comment만 수정 가능.
     * @param recordId
     * @param date
     * @param createdAt
     * @param updatedAt
     * @param comment
     * @param analyzedDataId
     */
    @Transactional
    public void putDietRecord(String recordId, String date, String createdAt, String updatedAt, String comment, String analyzedDataId) {
        DietRecord dietRecord = dietRecordRepository.findById(recordId).get();

        dietRecord.setDate(date);
        dietRecord.setCreatedAt(createdAt);
        dietRecord.setUpdatedAt(updatedAt);
        dietRecord.setComment(comment);

        dietRecordRepository.save(dietRecord);
    }

    /**
     * 영양분 계산하여 Nutrient 테이블에 저장
     * @param analyzedFood
     * @return
     */
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
            nutrientTotal.setDate(date);
            nutrientTotal.setYearMonth(date.substring(0,6));
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

    /**
     * 개별 Record 기록 삭제
     * @param recordId
     */
    @Transactional
    public void deleteDietRecord(Long recordId) {
        DietRecord dietRecord = dietRecordRepository.findById(String.valueOf(recordId)).get();
        Nutrient nutrient = dietRecord.getNutrient();

        // NutrientTotal 에서 값 계산
        subNutrientTotal(dietRecord.getDate(), nutrient);

        dietRecordRepository.delete(dietRecord);
        nutrientRepository.delete(nutrient);
    }

    /**
     * 값 계산
     * @param date
     * @param nutrient
     * @return
     */
    @Transactional
    public void subNutrientTotal(String date, Nutrient nutrient) {
        NutrientTotal nutrientTotal = nutrientTotalRepository.findNutrientTotalByDate(date);

        nutrientTotal.setTotalTan(nutrientTotal.getTotalTan() - nutrient.getTan());
        nutrientTotal.setTotalDan(nutrientTotal.getTotalDan() - nutrient.getDan());
        nutrientTotal.setTotalGi(nutrientTotal.getTotalGi() - nutrient.getGi());
        nutrientTotal.setTotalNa(nutrientTotal.getTotalNa() - nutrient.getNa());
        nutrientTotal.setTotalCal(nutrientTotal.getTotalCal() - nutrient.getCal());

        nutrientTotalRepository.save(nutrientTotal);
    }

    /**
     * 월별 record 기록 요청
     * @param kakaoId
     * @param yearMonth
     * @return
     */
    public List<ResponseDietRecord> getDietRecordList(String kakaoId, String yearMonth) {
        List<ResponseDietRecord> responseDietRecordList = new JSONArray();

        Users users = usersRepository.findByKakaoId(kakaoId).get();
        List<DietRecord> dietRecordList = dietRecordRepository.findAllByUsersAndYearMonth(users, yearMonth);

        dietRecordList.stream().map(record ->{
            ResponseDietRecord responseDietRecord = new ResponseDietRecord();
            AnalyzedData analyzedData = record.getAnalyzedData();

            responseDietRecord.setRecordId(record.getRecordId());
            responseDietRecord.setDate(record.getDate());
            responseDietRecord.setCreatedAt(record.getCreatedAt());
            responseDietRecord.setUpdatedAt(record.getUpdatedAt());
            responseDietRecord.setComment(record.getComment());
            responseDietRecord.setImgUrl(analyzedData.getImgUrl());
            responseDietRecord.setImgUrl(analyzedData.getAnalyzedImgUrl());
            responseDietRecord.setNutrient(record.getNutrient());

            responseDietRecordList.add(responseDietRecord);
            return null;
        });

        return responseDietRecordList;
    }
}
