package pangyo.makeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pangyo.makeat.Repository.UsersRepository;
import pangyo.makeat.dto.Users;

import java.io.IOException;

@Service
public class UserDataService {
    @Autowired
    UsersRepository usersRepository;

    public void saveUserData(String kakaoId) throws IOException {

        Users user = new Users();
        user.setKakaoId(kakaoId);
        usersRepository.save(user);
    }
}
