package com.example.owl.DTO;

public class JwtAuthenticationResponse {
    private String token;
    private String email;
    private boolean admin;

    public JwtAuthenticationResponse() {}

    public JwtAuthenticationResponse(String token, String email, boolean admin) {
        this.token = token;
        this.email = email;
        this.admin = admin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
