package org.example.deliverymatch.auth;

import lombok.RequiredArgsConstructor;
import org.example.deliverymatch.config.JwtService;
import org.example.deliverymatch.model.Adiministarteur;
import org.example.deliverymatch.model.Conducteur;
import org.example.deliverymatch.model.Expéditeur;
import org.example.deliverymatch.model.Utilisateur;
import org.example.deliverymatch.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UtilisateurRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthenticationResponse register(RegisterRequest request) {
        Utilisateur user;

        // Decide which subclass to create based on role
        switch (request.getRole()) {
            case ADMINISTRATEUR -> user = new Adiministarteur();
            case CONDUCTEUR -> user = new Conducteur();
            case EXPEDITEUR -> user = new Expéditeur();

            default -> throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }

        user.setNom(request.getNom()); // or request.getUsername()
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        repository.save(user); // use appropriate repository for the subclass

        String jwtToken = jwtService.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(jwtToken);
        return response;
    }
//    public AuthenticationResponse register(RegisterRequest request) {
//
//        var user = Utilisateur.builder()
//                .nom(request.getNom())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRole())
//                .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .accessToken(jwtToken)
//                .build();
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);


        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
