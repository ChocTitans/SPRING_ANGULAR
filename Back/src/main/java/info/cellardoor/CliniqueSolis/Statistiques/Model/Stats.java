package info.cellardoor.CliniqueSolis.Statistiques.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@Entity
@Table(name = "statsistiques")
public class Stats {
    @Id
    private  Long id;


}
