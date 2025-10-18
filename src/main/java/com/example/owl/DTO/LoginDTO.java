package com.example.owl.DTO;


public class LoginDTO {
    private String correo;
    private String contrasenia;

    public LoginDTO() {
    }

    public LoginDTO(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getcorreo() {
        return correo;
    }

    public void setcorreo(String correo) {
        this.correo = correo;
    }

    public String getcontrasenia() {
        return contrasenia;
    }

    public void setcontrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
