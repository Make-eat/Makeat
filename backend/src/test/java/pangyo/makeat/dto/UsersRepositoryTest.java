package pangyo.makeat.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pangyo.makeat.repository.UsersRepository;

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

        String kakaoId = "abc";

        user.setKakaoId(kakaoId);

        usersRepository.save(user);

        Users user2 = new Users();

        String kakaoId2 = "def";

        user.setKakaoId(kakaoId2);

        usersRepository.save(user2);

        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(1);
        assertThat(users.getUserId()).isEqualTo(2);
        System.out.println(users.getUserId());
    }
}