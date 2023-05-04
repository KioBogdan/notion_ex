package com.example.notion_ex.auth;

import com.example.notion_ex.config.JwtService;
import com.example.notion_ex.model.UserRole;
import com.example.notion_ex.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = com.example.notion_ex.model.User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                /*.username(request.getUsername())*/
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.CLIENT)
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),
                                                        request.getPassword())

        );

        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
