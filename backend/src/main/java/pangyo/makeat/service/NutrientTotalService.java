package pangyo.makeat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.entity.NutrientTotal;
import pangyo.makeat.entity.User;
import pangyo.makeat.repository.DietRecordRepository;
import pangyo.makeat.repository.NutrientTotalRepository;
import pangyo.makeat.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NutrientTotalService {
    NutrientTotalRepository nutrientTotalRepository;

    UserRepository userRepository;

    DietRecordRepository dietRecordRepository;

    public List<NutrientTotal> getNutrientTotalList(String kakaoId, String yearMonth) {
        User user = userRepository.findUserByKakaoId(kakaoId).get();
        List<NutrientTotal> nutrientTotalList = nutrientTotalRepository.findAllByUserAndYearMonth(user, yearMonth);

        return nutrientTotalList;
    }
}
