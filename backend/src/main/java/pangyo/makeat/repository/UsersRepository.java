package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pangyo.makeat.dto.UserInfo;
import pangyo.makeat.dto.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findByKakaoId(String kakaoId);
}