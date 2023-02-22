package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.dto.Nutrient;

public interface NutrientRepository extends JpaRepository<Nutrient, String> {
}
