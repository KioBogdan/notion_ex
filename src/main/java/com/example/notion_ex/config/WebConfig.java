package com.example.notion_ex.config;

import com.example.notion_ex.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.crypto.EncryptedPrivateKeyInfo;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final UserRepo userRepo;
    /*public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8081");
            }
        };
    }*/
    /*public UserDetailsService userDetailsService() {
        return username -> userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }*/
    @Bean
    public UserDetailsService userDetailsMail() {
        return email -> userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider autProvider = new DaoAuthenticationProvider();

        //minimum required information
        autProvider.setUserDetailsService(userDetailsMail());
        autProvider.setPasswordEncoder(passwordEncoder());
        return autProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
