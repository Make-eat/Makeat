package pangyo.makeat.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.dto.Users;
import pangyo.makeat.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Transactional
    public void saveUser(String kakaoId){
        Users users = new Users();
        users.setKakaoId(kakaoId);
        usersRepository.save(users);
    }

    //개발자 확인용 코드. 내용 아니라 주소 리턴돼서 수정 필요
    public void findAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        for (int i=0; i < usersList.size(); i++){
            System.out.println(usersList.get(i));
        }
    }

    public Optional<Users> findUsers(String kakaoId) {
        Optional<Users> users = usersRepository.findUsersByKakaoId(kakaoId);
        return users;
    }

    public boolean checkExistUsers(String kakaoId) {
        Optional<Users> users = usersRepository.findUsersByKakaoId(kakaoId);
        if (users.isPresent()) { //이미 가입된 사용자
            return true;
        }
        //미가입 사용자->새로 가입시키기
        saveUser(kakaoId);
        return false;
    }
}
