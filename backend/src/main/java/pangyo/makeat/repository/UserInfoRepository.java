package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pangyo.makeat.dto.UserInfo;
import pangyo.makeat.dto.Users;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}