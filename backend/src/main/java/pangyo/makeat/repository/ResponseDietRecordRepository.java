package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.dto.responses.ResponseDietRecord;
import pangyo.makeat.entity.DietRecord;
import pangyo.makeat.entity.User;

import java.time.YearMonth;
import java.util.List;

/**
 * 테스트 진행중 입니당~ - 승현
 */
public interface ResponseDietRecordRepository extends JpaRepository<DietRecord, String> {
    List<ResponseDietRecord> findAllByUserAndYearMonth(User user, YearMonth yearMonth);
}
