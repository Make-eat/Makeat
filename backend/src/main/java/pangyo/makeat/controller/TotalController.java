package pangyo.makeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pangyo.makeat.entity.NutrientTotal;
import pangyo.makeat.service.NutrientTotalService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/total")
public class TotalController {

    @Autowired
    NutrientTotalService nutrientTotalService;


    /**
     * 식단 통계 정보 조회
     */
    @GetMapping("/{kakaoId}")
    public List<NutrientTotal> getNutrientTotal(
            @PathVariable String kakaoId,
            @RequestParam("yearMonth") String yearMonth
    ) {

        return nutrientTotalService.getNutrientTotalList(kakaoId, yearMonth);
    }
}
