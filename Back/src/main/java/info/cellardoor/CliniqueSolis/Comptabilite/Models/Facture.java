package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "factures")
public class Facture {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer factureId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id")
    private Patient patient;
    private Long montant ;
    private String type_service;

}
