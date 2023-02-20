//package pangyo.makeat.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import pangyo.makeat.dto.Users;
//
//import java.util.List;
//
//@Service
//public class UserService {
//    @Autowired
//    private UsersRepository usersRepository;
//
//    /**
//     * 회원 가입 // 이게 필요할까?
//     */
//    public Long join(Users users) {
//        validateDuplicateMember(users); // 중복 회원 검증
//        usersRepository.save(users);
//
//        return users.getUserId();
//    }
//
//    private void validateDuplicateMember(Users users) {
//        List<Users> findUsers = usersRepository.findByName(users.getKakaoId());
//        if (!findUsers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
//}
