package info.cellardoor.CliniqueSolis.Patient.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "antecedents")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Antecedent {
    @Id
    @GeneratedValue
    private Integer antecedentId;
    private String groupeSanguin;
    private String allergies;
    private String maladiesChroniques;
    private String chirurgies;
    private String antecedentsFamiliaux;
}
