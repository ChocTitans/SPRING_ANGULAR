package info.cellardoor.CliniqueSolis.Comptabilite.Services;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.TresorerieResponse.TresorieResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.TresorerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@RequiredArgsConstructor
@Service
public class TresorerieService {
    public final TresorerieRepository tresorerieRepository;

    public TresorieResponse getTresorerie() {
        var tresorerie = tresorerieRepository.findById(1).orElseThrow(() ->new NoSuchElementException("Error"));
        return TresorieResponse.builder()
                .capital(tresorerie.getCapital())
                .build();
    }
}
