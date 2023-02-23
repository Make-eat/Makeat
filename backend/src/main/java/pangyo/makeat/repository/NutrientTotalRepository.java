package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.dto.NutrientTotal;
import pangyo.makeat.dto.Users;

import java.util.List;
import java.util.Optional;

public interface NutrientTotalRepository extends JpaRepository<NutrientTotal, String> {
    NutrientTotal findNutrientTotalByDate(String date);
    List<NutrientTotal> findAllByUsersAndYearMonth(Users users, String yearMonth);
}
