package pangyo.makeat.service;

import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.dto.InfoGender;
import pangyo.makeat.dto.UserInfo;
import pangyo.makeat.dto.Users;
import pangyo.makeat.repository.UserInfoRepository;
import pangyo.makeat.repository.UsersRepository;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UsersRepository usersRepository;

//    public Users findUsers(String kakaoId) throws IOException {
//        return usersRepository.findUsersByKakaoId(kakaoId).get();
//    }

    @Transactional
    public void saveUserInfo(String kakaoId, int age, String gender, int height, int weight, float bmi) throws IOException {
        UserInfo userinfo = new UserInfo();
        Users users = usersRepository.findUsersByKakaoId(kakaoId).get(); //해당 kakaoId의 user 가져와서
        userinfo.setUserId(users);
        userinfo.setAge(age);
        userinfo.setGender(InfoGender.valueOf(gender));
        userinfo.setHeight(height);
        userinfo.setWeight(weight);
        userinfo.setBmi(bmi);

        userInfoRepository.save(userinfo);
    }

    @Transactional
    public Optional<UserInfo> findUserInfo(String kakaoId) {
        Users user = usersRepository.findUsersByKakaoId(kakaoId).get();
        log.info("findUserInfo, user info = {}", userInfoRepository.findByUserId(user));

        return userInfoRepository.findByUserId(user);
    }
}