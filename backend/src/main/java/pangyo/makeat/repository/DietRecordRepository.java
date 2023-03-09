package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pangyo.makeat.entity.DietRecord;
import pangyo.makeat.entity.User;

import java.util.List;

@Repository
public interface DietRecordRepository extends JpaRepository<DietRecord, String> {
    List<DietRecord> findAllByUserAndYearMonth(User user, String yearMonth);
}
