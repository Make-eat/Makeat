package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.dto.NutrientTotal;

import java.util.Optional;

public interface NutrientTotalRepository extends JpaRepository<NutrientTotal, String> {
    Optional<NutrientTotal> findByDate(String date);
}
