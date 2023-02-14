package pangyo.makeat.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

//    @AfterEach
//    public void cleanup() {
//        usersRepository.deleteAll();
//    }

    @Test
    public void saveAndReadPosts() {
        //given
        Users user = new Users();

        Long kakaoId = 1L;
        String userToken = "a1b2";

        user.setKakaoId(kakaoId);
        user.setUserToken(userToken);

        usersRepository.save(user);

        Users user2 = new Users();

        Long kakaoId2 = 2L;
        String userToken2 = "a1b2c3";

        user.setKakaoId(kakaoId2);
        user.setUserToken(userToken2);

        usersRepository.save(user2);

        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(1);
        assertThat(users.getUserId()).isEqualTo(2);
        System.out.println(users.getUserId());
    }
}