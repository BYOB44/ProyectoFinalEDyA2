package com.empresa.facturacion.dto;

public class AuthResponse {
    private String token;
    private String username;
    private String role;
    private Long workerId;

    public AuthResponse(String token, String username, String role, Long workerId) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.workerId = workerId;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public Long getWorkerId() { return workerId; }
}
