package info.cellardoor.CliniqueSolis.Consultation.Models;

import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private Integer consultationId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVousId;

    @Column(name = "date_consultation")
        private String dateConsultation;

    @Column(name = "description")
    private String description;
    //private List<Prescription> prescriptions;

}
