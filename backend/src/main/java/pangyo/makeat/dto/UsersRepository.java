package pangyo.makeat.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
}