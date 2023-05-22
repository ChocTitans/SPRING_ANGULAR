package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.TresorerieResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TresorieResponse {
@JsonProperty("capital")
    private Long capital;
}
