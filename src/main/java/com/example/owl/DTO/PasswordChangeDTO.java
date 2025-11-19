package com.example.owl.DTO;

public class PasswordChangeDTO {

    private String contrasenia;

    public PasswordChangeDTO() {

    }

 
    public PasswordChangeDTO(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
