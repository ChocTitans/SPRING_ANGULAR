package info.cellardoor.CliniqueSolis.Statistiques.Contoller;

import info.cellardoor.CliniqueSolis.Statistiques.Model.Stats;
import info.cellardoor.CliniqueSolis.Statistiques.Service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    final public StatsService statsService;
@Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/GET")
    public List<Stats> getStats() {
        return statsService.getStats() ;
    }
    @PostMapping("/POST")
    public void postStats(@RequestBody Stats statistique){
        System.out.println(statistique);

    }



}
