package pangyo.makeat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pangyo.makeat.entity.AnalyzedData;

public interface AnalyzeRepository extends JpaRepository<AnalyzedData, String> {
}
