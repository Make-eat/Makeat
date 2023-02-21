package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.dto.AnalyzedData;

public interface AnalyzeRepository extends JpaRepository<AnalyzedData, String> {
}
