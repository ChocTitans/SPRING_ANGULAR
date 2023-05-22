package info.cellardoor.CliniqueSolis.Statistiques.Service;

import info.cellardoor.CliniqueSolis.Statistiques.Model.Stats;
import info.cellardoor.CliniqueSolis.Statistiques.Model.StatsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StatsService {
    private final StatsRepository statistiqueRepository;
    @Autowired
    public StatsService(StatsRepository statistiqueRepository) {
        this.statistiqueRepository = statistiqueRepository;
    }
public  List<Stats> getStats() {
        return statistiqueRepository.findAll();
    }

}
