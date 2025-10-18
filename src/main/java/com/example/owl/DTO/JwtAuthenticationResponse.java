package com.example.owl.DTO;
public class JwtAuthenticationResponse {
    private String token;
    private String email;
    private boolean is_admin;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token, String email, boolean is_admin) {
        this.token = token;
        this.email = email;
        this.is_admin = is_admin;
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

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
