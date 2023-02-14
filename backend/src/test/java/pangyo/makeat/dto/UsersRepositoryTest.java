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

    @AfterEach
    public void cleanup() {
        usersRepository.deleteAll();
    }

    @Test
    public void saveAndReadPosts() {
        //given
        Users user = new Users();

        Long userId = 1L;
        Long kakaoId = 1L;
        String userToken = "a1b2";

        user.setKakaoId(kakaoId);
        user.setUserToken(userToken);
        user.setUserId(userId);

        usersRepository.save(user);

        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(0);
        assertThat(users.getUserId()).isEqualTo(userId);
        System.out.println(userId);
    }
}