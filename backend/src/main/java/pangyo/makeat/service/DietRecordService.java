package pangyo.makeat.service;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pangyo.makeat.dto.responses.ResponseDietRecord;
import pangyo.makeat.entity.*;
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
    NutrientTotalRepository nutrientTotalRepository;

    /**
     * 식단 기록 저장
     */
    @Transactional
    public void saveDietRecord(
            String kakaoId, String date, String time, String comment, String imgUrl, String analyzedImgUrl,
            float carb, float protein, float fat, float na, float kcal
    ) {
        User user = usersRepository.findUsersByKakaoId(kakaoId).get();

        /**
         * AnalyzedData 저장 -> Nutrient 저장 -> NutrientTotal 에 반영 -> DietRecord 저장
         */

        // AnalyzedData 저장
        AnalyzedData analyzedData = new AnalyzedData();
        analyzedData.setUser(user);
        analyzedData.setImgUrl(imgUrl);
        analyzedData.setAnalyzedImgUrl(analyzedImgUrl);

        AnalyzedData savedAnalyzedData = analyzeRepository.save(analyzedData);

        // Nutrient 저장
        Nutrient nutrient = new Nutrient();
        nutrient.setCarb(carb);
        nutrient.setProtein(protein);
        nutrient.setFat(fat);
        nutrient.setNa(na);
        nutrient.setKcal(kcal);

        Nutrient savedNutrient = nutrientRepository.save(nutrient);

        // NutrientTotal 에 반영
        // date 와 user 비교해서 TotalNutrient 에 반영
        NutrientTotal savedNutrientTotal = calculateNutrientTotal(date, user, savedNutrient);

        // DietRecord 저장
        DietRecord dietRecord = new DietRecord();

        dietRecord.setNutrientTotal(savedNutrientTotal);
        dietRecord.setUser(user);
        dietRecord.setNutrient(savedNutrient);
        dietRecord.setAnalyzedData(analyzedData);
        dietRecord.setDate(date);
        dietRecord.setTime(time);
        dietRecord.setYearMonth(date.substring(0, 6));
        dietRecord.setComment(comment);

        dietRecordRepository.save(dietRecord);
    }

    /**
     * 식단 기록 수정
     * 이미지와 nutrient 는 수정 불가.
     * 날짜와 시간, comment만 수정 가능.
     */
    @Transactional
    public void putDietRecord(
            String recordId, String date, String time, String comment
    ) {
        DietRecord dietRecord = dietRecordRepository.findById(recordId).get();

        dietRecord.setDate(date);
        dietRecord.setTime(time);
        dietRecord.setComment(comment);

        dietRecordRepository.save(dietRecord);
    }

    /**
     * 영양분 통계치를 계산하고 NutrientTotal 테이블에 저장.
     * @param date
     * @param nutrient
     * @param user
     * @return NutrientTotal
     */
    @Transactional
    public NutrientTotal calculateNutrientTotal(String date, User user, Nutrient nutrient) {
        NutrientTotal nutrientTotal = nutrientTotalRepository.findNutrientTotalByDateAndUser(date, user);

        if (nutrientTotal == null) {    // 테이블이 없는 경우
            nutrientTotal = new NutrientTotal();
            nutrientTotal.setTotalCarb(nutrient.getCarb());
            nutrientTotal.setTotalProtein(nutrient.getProtein());
            nutrientTotal.setTotalFat(nutrient.getFat());
            nutrientTotal.setTotalNa(nutrient.getNa());
            nutrientTotal.setTotalKcal(nutrient.getKcal());
            nutrientTotal.setDate(date);
            nutrientTotal.setYearMonth(date.substring(0,6));
            nutrientTotal.setUser(user);

        }else{  // 테이블이 있는 경우
            // DietRecord 를 findAll 해서 매번 계산해 주는게 베스트일거같긴 한데.. 귀찮아 ㅋ 나중에 고민해봄
            nutrientTotal.setTotalCarb(nutrient.getCarb() + nutrientTotal.getTotalCarb());
            nutrientTotal.setTotalProtein(nutrient.getProtein() + nutrientTotal.getTotalProtein());
            nutrientTotal.setTotalFat(nutrient.getFat() + nutrientTotal.getTotalFat());
            nutrientTotal.setTotalNa(nutrient.getNa() + nutrientTotal.getTotalNa());
            nutrientTotal.setTotalKcal(nutrient.getKcal() + nutrientTotal.getTotalKcal());

        }

        return nutrientTotalRepository.save(nutrientTotal);
    }

    /**
     * 개별 Record 기록 삭제
     * @param recordId
     */
//    @Transactional
//    public void deleteDietRecord(Long recordId) {
//        DietRecord dietRecord = dietRecordRepository.findById(String.valueOf(recordId)).get();
//        Nutrient nutrient = dietRecord.getNutrient();
//
//        // NutrientTotal 에서 값 계산
//        subNutrientTotal(dietRecord.getDate(), nutrient);
//
//        dietRecordRepository.delete(dietRecord);
//        nutrientRepository.delete(nutrient);
//    }

    /**
     * 값 계산
     * @param date
     * @param nutrient
     * @return
     */
//    @Transactional
//    public void subNutrientTotal(String date, Nutrient nutrient) {
//        NutrientTotal nutrientTotal = nutrientTotalRepository.findNutrientTotalByDate(date);
//
//        nutrientTotal.setTotalCarb(nutrientTotal.getTotalCarb() - nutrient.getCarbohydrate());
//        nutrientTotal.setTotalProtein(nutrientTotal.getTotalProtein() - nutrient.getProtein());
//        nutrientTotal.setTotalFat(nutrientTotal.getTotalFat() - nutrient.getFat());
//        nutrientTotal.setTotalNa(nutrientTotal.getTotalNa() - nutrient.getNa());
//        nutrientTotal.setTotalKcal(nutrientTotal.getTotalKcal() - nutrient.getKcal());
//
//        nutrientTotalRepository.save(nutrientTotal);
//    }

    /**
     * 월별 record 기록 요청
     * @param kakaoId
     * @param yearMonth
     * @return
     */
    public List<ResponseDietRecord> getDietRecordList(String kakaoId, String yearMonth) {
        List<ResponseDietRecord> responseDietRecordList = new JSONArray();

        User user = usersRepository.findUsersByKakaoId(kakaoId).get();
        List<DietRecord> dietRecordList = dietRecordRepository.findAllByUserAndYearMonth(user, yearMonth);

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
