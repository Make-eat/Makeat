package pangyo.makeat.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pangyo.makeat.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

//    @AfterEach
//    public void cleanup() {
//        usersRepository.deleteAll();
//    }

    @Test
    public void saveAndReadPosts() {
        //given
        User user = new User();

        String kakaoId = "abc";

        user.setKakaoId(kakaoId);

        userRepository.save(user);

        User user2 = new User();

        String kakaoId2 = "def";

        user.setKakaoId(kakaoId2);

        userRepository.save(user2);

        //when
        List<User> userList = userRepository.findAll();

        //then
        User users = userList.get(1);
        assertThat(users.getUserId()).isEqualTo(2);
        System.out.println(users.getUserId());
    }
}