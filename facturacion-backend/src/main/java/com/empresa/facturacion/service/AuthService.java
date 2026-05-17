package com.empresa.facturacion.service;

import com.empresa.facturacion.config.JwtService;
import com.empresa.facturacion.dto.AuthResponse;
import com.empresa.facturacion.dto.LoginRequest;
import com.empresa.facturacion.model.UserAccount;
import com.empresa.facturacion.repository.UserAccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserAccountRepository userAccountRepository;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, UserAccountRepository userAccountRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userAccountRepository = userAccountRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserAccount user = userAccountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtService.generateToken(user);
        Long workerId = user.getWorker() != null ? user.getWorker().getId() : null;

        return new AuthResponse(token, user.getUsername(), user.getRole().name(), workerId);
    }
}
