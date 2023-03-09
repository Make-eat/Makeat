package pangyo.makeat.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.entity.User;
import pangyo.makeat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    UserRepository userRepository;

    @Transactional
    public void saveUser(String kakaoId){
        User user = new User();
        user.setKakaoId(kakaoId);
        userRepository.save(user);
    }

    //개발자 확인용 코드. 내용 아니라 주소 리턴돼서 수정 필요
    public void findAllUsers() {
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++){
            log.info("{}", userList.get(i));
        }
    }

    public Optional<User> findUsers(String kakaoId) {
        Optional<User> users = userRepository.findUserByKakaoId(kakaoId);
        return users;
    }

    public boolean checkExistUsers(String kakaoId) {
        Optional<User> users = userRepository.findUserByKakaoId(kakaoId);
        if (users.isPresent()) { //이미 가입된 사용자
            return true;
        }
        //미가입 사용자->새로 가입시키기
        saveUser(kakaoId);
        return false;
    }
}
