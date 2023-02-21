package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pangyo.makeat.service.AnalyzeService;

@Slf4j
@RestController
@RequestMapping("/analyze")
public class AnalyzeController {
    @Autowired
    AnalyzeService as;

    @PostMapping("/{kakaoId}")
    public void analyzeImage(@PathVariable String kakaoId) {

    }

}
