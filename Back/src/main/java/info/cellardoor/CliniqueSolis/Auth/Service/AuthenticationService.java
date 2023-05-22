package info.cellardoor.CliniqueSolis.Auth.Service;


import info.cellardoor.CliniqueSolis.Auth.Http.Request.AuthenticationRequest;
import info.cellardoor.CliniqueSolis.Auth.Http.Response.LoginResponse;
import info.cellardoor.CliniqueSolis.Patient.Http.Request.RegisterRequest;
import info.cellardoor.CliniqueSolis.Auth.Http.Response.AuthenticationResponse;
import info.cellardoor.CliniqueSolis.Auth.Models.Token.Token;
import info.cellardoor.CliniqueSolis.Auth.Models.Token.TokenRepository;
import info.cellardoor.CliniqueSolis.Auth.Models.Token.TokenType;
import info.cellardoor.CliniqueSolis.Auth.Models.User.Roles;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import info.cellardoor.CliniqueSolis.Auth.Models.User.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .mdp(passwordEncoder.encode(request.getPassword()))
                .role(Roles.ROLE_UTILISATEUR)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(user.getRole().name())
                .build();
    }

    public LoginResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user); If we want to limit the number of active sessions
        saveUserToken(user, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .role(user.getRole().name())
                .build();
    }

    void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponse refreshToken(
            HttpServletRequest request
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        User user = null;

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Refresh token is missing");
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(refreshToken);
        if (userEmail != null) {
            user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
            }
        }
        assert user != null;
        return AuthenticationResponse.builder()
                .accessToken(refreshToken)
                .refreshToken(refreshToken)
                .role(user.getRole().name())
                .build();
    }
}