package info.cellardoor.CliniqueSolis.Auth.Http.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListUserResponse {
    private List<UserResponse> users;

}