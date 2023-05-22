package info.cellardoor.CliniqueSolis.Comptabilite.Services;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FactureRequest.FactureRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse.FactureResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Facture;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FactureRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor

public class FactureService {
    public final FactureRepository factureRepository;
    public final PatientRepository patientRepository;

    public FactureResponse getFactureById(Integer id) {
        var facture= factureRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non facture trouve"));
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .cin(facture.getPatient().getCin())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }
    public FactureResponse createFacture(FactureRequest factureRequest ){
        var patient= patientRepository.findByCin(factureRequest.getCin()).orElseThrow(() ->new NoSuchElementException("non patient trouve"));
        var facture = Facture.builder()
                .montant(factureRequest.getMontant())
                .patient(patient)
                .type_service(factureRequest.getType_service()).build();
        factureRepository.save(facture);
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .cin(facture.getPatient().getCin())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }
    public FactureResponse deleteFactureById(Integer id) {
        var facture= factureRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non facture trouve"));
        factureRepository.delete(facture);
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .cin(facture.getPatient().getCin())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }
    public FactureResponse updateFactureById(Integer id, FactureRequest factureRequest) {
        var facture= factureRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non facture trouve"));
        facture.setMontant(factureRequest.getMontant());
        facture.setType_service(factureRequest.getType_service());
        factureRepository.save(facture);
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .cin(facture.getPatient().getCin())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }


    }

