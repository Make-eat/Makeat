package pangyo.makeat.service;

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
public class NutrientTotalService {
    @Autowired
    NutrientTotalRepository nutrientTotalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DietRecordRepository dietRecordRepository;

    public List<NutrientTotal> getNutrientTotalList(String kakaoId, String yearMonth) {
        User user = userRepository.findUserByKakaoId(kakaoId).get();
        List<NutrientTotal> nutrientTotalList = nutrientTotalRepository.findAllByUserAndYearMonth(user, yearMonth);

        return nutrientTotalList;
    }
}
