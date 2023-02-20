package pangyo.makeat.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.dto.InfoGender;
import pangyo.makeat.dto.UserInfo;
import pangyo.makeat.dto.Users;
import pangyo.makeat.repository.UserInfoRepository;
import pangyo.makeat.repository.UsersRepository;

import java.io.IOException;

@Service
public class UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UsersRepository usersRepository;

    @Transactional
    public void saveUserInfo(String kakaoId, int age, String gender, int height, int weight, float bmi) throws IOException {
        UserInfo userinfo = new UserInfo();
        Users user = usersRepository.findById(kakaoId).get(); //해당 kakaoId의 user 가져와서
        userinfo.setUserId(user);
        userinfo.setAge(age);
        userinfo.setGender(InfoGender.valueOf(gender));
        userinfo.setHeight(height);
        userinfo.setWeight(weight);
        userinfo.setBmi(bmi);
        userInfoRepository.save(userinfo);
    }
}