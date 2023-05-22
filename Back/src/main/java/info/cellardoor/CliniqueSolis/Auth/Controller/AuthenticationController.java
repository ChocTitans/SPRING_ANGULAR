package info.cellardoor.CliniqueSolis.Auth.Controller;


import info.cellardoor.CliniqueSolis.Auth.Http.Response.LoginResponse;
import info.cellardoor.CliniqueSolis.Auth.Service.AuthenticationService;
import info.cellardoor.CliniqueSolis.Auth.Http.Request.AuthenticationRequest;
import info.cellardoor.CliniqueSolis.Patient.Http.Request.RegisterRequest;
import info.cellardoor.CliniqueSolis.Auth.Http.Response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest req
    ) {
        return ResponseEntity.ok(authenticationService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody AuthenticationRequest req
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(req));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }
}