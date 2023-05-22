package info.cellardoor.CliniqueSolis.Consultation.Service;

import info.cellardoor.CliniqueSolis.Consultation.Http.Request.ConsultationRequest;
import info.cellardoor.CliniqueSolis.Consultation.Http.Request.PrescriptionRequest;
import info.cellardoor.CliniqueSolis.Consultation.Http.Response.ConsultationResponse;
import info.cellardoor.CliniqueSolis.Consultation.Http.Response.ListConsultationResponse;
import info.cellardoor.CliniqueSolis.Consultation.Http.Response.PrescriptionResponse;
import info.cellardoor.CliniqueSolis.Consultation.Models.Consultation;
import info.cellardoor.CliniqueSolis.Consultation.Models.ConsultationRepository;
import info.cellardoor.CliniqueSolis.Consultation.Models.Prescription;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Medecin.Models.MedecinRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.ListRendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse.RendezVousResponse;
import info.cellardoor.CliniqueSolis.RendezVous.Http.Request.RendezVousRequest;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    public final ConsultationRepository consultationRepository;
    public final RendezVousRepository rendezVousRepository;

    @Autowired
    public ConsultationService(RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }
    private RendezVous getRendezVousById(Integer rendezVousId) {
        Optional<RendezVous> rendezVous = rendezVousRepository.findById(rendezVousId);
        if (rendezVous.isEmpty()) {
            throw new IllegalArgumentException("RendezVous not found");
        }
        return rendezVous.get();
    }
    public ConsultationResponse  getConsultationById(Integer id) {
        var cn = consultationRepository.findByRendezVousId(id)
                .orElseThrow(() -> new NoSuchElementException("Consultation   non trouvé"));
        return ConsultationResponse.builder()
                .consultationId(cn.getConsultationId())
                .rendezVousId(cn.getRendezVousId().getRendezVousId())
                .observations(cn.getDescription())
                .dateConsultation(cn.getDateConsultation())
//                .prescriptions(cn.getPrescriptions().stream().map(prescription -> PrescriptionResponse.builder()
//                        .prescriptionId(prescription.getPrescriptionId())
//                        .medicament(prescription.getMedicament())
//                        .duree(prescription.getDuree())
//                        .build()).collect(Collectors.toList()))
                .build();
    }
    public ConsultationResponse createConsultation(ConsultationRequest consultationRequest) {
        RendezVous rendezVous = getRendezVousById(consultationRequest.getRendezVousId());
        Consultation consultation = Consultation.builder()
                .consultationId(consultationRequest.getConsultationId())
                .rendezVousId(rendezVous)
                .dateConsultation(consultationRequest.getDateConsultation())
                .description(consultationRequest.getObservations())
//                .prescriptions(consultationRequest.getPrescriptions().stream().map(prescription -> Prescription.builder()
//                        .prescriptionId(prescription.getPrescriptionId())
//                        .medicament(prescription.getMedicament())
//                        .duree(prescription.getDuree())
//                        .build()).collect(Collectors.toList()))
                .build();
        consultationRepository.save(consultation);
        return ConsultationResponse.builder()
                .consultationId(consultation.getConsultationId())
                .rendezVousId(consultation.getRendezVousId().getRendezVousId())
                .observations(consultation.getDescription())
                .dateConsultation(consultation.getDateConsultation())
//                .prescriptions(consultationRequest.getPrescriptions().stream().map(prescription -> PrescriptionResponse.builder()
//                        .medicament(prescription.getMedicament())
//                        .duree(prescription.getDuree())
//                        .build()).collect(Collectors.toList()))
                .build();
    }
    public ConsultationResponse  updateConsultationById(Integer id, ConsultationRequest consultationRequest) {
        var cn = consultationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Consultation   non trouvé"));
        cn.setConsultationId(consultationRequest.getConsultationId());
        cn.setDateConsultation(consultationRequest.getDateConsultation());
        cn.setRendezVousId(getRendezVousById(consultationRequest.getRendezVousId()));
        cn.setDescription(consultationRequest.getObservations());
        var savedConsultation = consultationRepository.save(cn);
        return ConsultationResponse.builder()
                .consultationId(cn.getConsultationId())
                .rendezVousId(cn.getRendezVousId().getRendezVousId())
                .observations(cn.getDescription())
                .dateConsultation(cn.getDateConsultation())
//                .prescriptions(consultationRequest.getPrescriptions().stream().map(prescription -> PrescriptionResponse.builder()
//                        .medicament(prescription.getMedicament())
//                        .duree(prescription.getDuree())
//                        .build()).collect(Collectors.toList()))
                .build();
    }
    public void deleteConsultationById(Integer consultationId) {
        var consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new NoSuchElementException("Consultation non trouvé"));
        consultationRepository.delete(consultation);
    }
    public ConsultationResponse getConsultationResponse(ConsultationRequest consultationRequest, Consultation consultation) {
        BeanUtils.copyProperties(consultationRequest, consultation, getNullPropertyNames(consultationRequest));
        var savedRdv = consultationRepository.save(consultation);
        return ConsultationResponse.builder()
                .consultationId(savedRdv.getConsultationId())
                .rendezVousId(savedRdv.getRendezVousId().getRendezVousId())
                .observations(savedRdv.getDescription())
                .dateConsultation(savedRdv.getDateConsultation())
//                .prescriptions(consultationRequest.getPrescriptions().stream().map(prescription -> PrescriptionResponse.builder()
//                        .medicament(prescription.getMedicament())
//                        .duree(prescription.getDuree())
//                        .build()).collect(Collectors.toList()))
                .build();
    }
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }



}
