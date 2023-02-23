package pangyo.makeat.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.dto.DietRecord;
import pangyo.makeat.dto.NutrientTotal;
import pangyo.makeat.dto.Users;
import pangyo.makeat.repository.DietRecordRepository;
import pangyo.makeat.repository.NutrientTotalRepository;
import pangyo.makeat.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NutrientTotalService {
    @Autowired
    NutrientTotalRepository nutrientTotalRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    DietRecordRepository dietRecordRepository;

    public List<NutrientTotal> getNutrientTotalList(String kakaoId, String yearMonth) {
        Users users = usersRepository.findUsersByKakaoId(kakaoId).get();
        List<NutrientTotal> nutrientTotalList = nutrientTotalRepository.findAllByUsersAndYearMonth(users, yearMonth);

        return nutrientTotalList;
    }
}
