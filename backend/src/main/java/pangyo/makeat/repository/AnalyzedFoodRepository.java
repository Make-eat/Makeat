package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.dto.AnalyzedFood;

import java.util.Optional;

public interface AnalyzedFoodRepository extends JpaRepository<AnalyzedFood, String> {
}
