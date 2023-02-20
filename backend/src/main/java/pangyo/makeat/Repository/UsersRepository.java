package pangyo.makeat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pangyo.makeat.dto.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
}