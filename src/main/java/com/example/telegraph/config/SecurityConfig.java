package com.example.telegraph.config;

import com.example.telegraph.filter.JwtTokenFilter;
import com.example.telegraph.service.AuthenticationService;
import com.example.telegraph.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.http.HttpRequest;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http.csrf().disable().authorizeHttpRequests((authorizer)->{
          authorizer.requestMatchers("/api/v1/auth/**").
                  permitAll().
                  anyRequest().
                  authenticated();
      }).sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
              addFilterBefore(new JwtTokenFilter(authenticationService,jwtService),
                      UsernamePasswordAuthenticationFilter.class).build();
    }
}
