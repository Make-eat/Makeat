package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.entity.NutrientTotal;
import pangyo.makeat.entity.User;

import java.util.List;

public interface NutrientTotalRepository extends JpaRepository<NutrientTotal, String> {
    NutrientTotal findNutrientTotalByDateAndUser(String date, User user);
    List<NutrientTotal> findAllByUserAndYearMonth(User user, String yearMonth);
}
