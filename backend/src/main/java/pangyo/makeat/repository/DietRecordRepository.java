package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pangyo.makeat.dto.DietRecord;

import java.util.Optional;

@Repository
public interface DietRecordRepository extends JpaRepository<DietRecord, String> {
    Optional<DietRecord> findDietRecordByUserId(Long userId);

}
