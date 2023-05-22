package info.cellardoor.CliniqueSolis.Demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import info.cellardoor.CliniqueSolis.RendezVous.Models.RendezVous;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo-controller")
public class DemoController {
        @RequestMapping("/user")
        @PreAuthorize("hasAnyRole({'ROLE_UTILISATEUR', 'ROLE_ADMIN'})")
        public ResponseEntity<String> helloUser() {
                ObjectMapper objectMapper = new ObjectMapper();
                String message = "Hello User from secure controller!";
                String jsonResponse = "";
                try {
                        jsonResponse = objectMapper.writeValueAsString(message);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return ResponseEntity.ok(jsonResponse);
        }
        @RequestMapping("/admin")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public ResponseEntity<String> helloAdmin() {
                ObjectMapper objectMapper = new ObjectMapper();
                String message = "Hello Admin from secure controller!";
                String jsonResponse = "";
                try {
                        jsonResponse = objectMapper.writeValueAsString(message);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return ResponseEntity.ok(jsonResponse);
        }
}