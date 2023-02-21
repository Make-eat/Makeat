package pangyo.makeat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pangyo.makeat.repository.AnalyzeRepository;

@Service
public class AnalyzeService {

    @Autowired
    AnalyzeRepository analyzeRepository;

}
