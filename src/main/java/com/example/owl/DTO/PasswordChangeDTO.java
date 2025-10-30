package com.example.owl.DTO;

public class PasswordChangeDTO {
    private String contrasenia;

    // Constructor vacío
    public PasswordChangeDTO() {
    }

    // Constructor con parámetros
    public PasswordChangeDTO(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    // Getters y Setters
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
