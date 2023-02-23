package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pangyo.makeat.dto.DietRecord;
import pangyo.makeat.dto.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface DietRecordRepository extends JpaRepository<DietRecord, String> {
    List<DietRecord> findAllByUsersAndYearMonth(Users users, String yearMonth);
}
