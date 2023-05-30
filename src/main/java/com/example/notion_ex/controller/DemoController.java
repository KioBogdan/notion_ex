package com.example.notion_ex.controller;

import com.example.notion_ex.auth.AuthenticationRequest;
import com.example.notion_ex.auth.AuthenticationResponse;
import com.example.notion_ex.auth.AuthenticationService;
import com.example.notion_ex.auth.RegisterRequest;
import com.example.notion_ex.config.JwtService;
import com.example.notion_ex.dto.FinancialActivityDTO;
import com.example.notion_ex.model.User;
import com.example.notion_ex.service.FinancialActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor

public class DemoController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final FinancialActivityService financialActivityService;
    private final JwtService jwtUtil;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("And we say bye bye");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(user)
                    )
                    .body(user);
        } catch(BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<FinancialActivityDTO> getXmlData() {
        return financialActivityService.findAllDTO();
    }
}
