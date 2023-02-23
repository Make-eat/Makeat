package pangyo.makeat.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.dto.AnalyzedData;
import pangyo.makeat.dto.AnalyzedFood;
import pangyo.makeat.dto.Users;
import pangyo.makeat.repository.AnalyzeRepository;
import pangyo.makeat.repository.UsersRepository;

import java.util.Optional;

@Service
public class AnalyzeService {

    @Autowired
    AnalyzeRepository analyzeRepository;
    @Autowired
    UsersRepository usersRepository;

    @Transactional
    public AnalyzedData saveData(String kakaoId,String imgUrl, String analyzedImgUrl){
        AnalyzedData analyzedData = new AnalyzedData();
        Optional<Users> users = usersRepository.findUsersByKakaoId(kakaoId);
        analyzedData.setImgUrl(imgUrl);
        analyzedData.setUsers(users.get());
        analyzedData.setAnalyzedImgUrl(analyzedImgUrl);
        return analyzeRepository.save(analyzedData);
    }
}
