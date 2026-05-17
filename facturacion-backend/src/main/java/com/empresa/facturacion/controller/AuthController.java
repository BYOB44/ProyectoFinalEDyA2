package com.empresa.facturacion.controller;

import com.empresa.facturacion.dto.AuthResponse;
import com.empresa.facturacion.dto.LoginRequest;
import com.empresa.facturacion.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
