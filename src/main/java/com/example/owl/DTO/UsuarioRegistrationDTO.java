package com.example.owl.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioRegistrationDTO{
    private String nombre;
    private String correo;
    private String contrasenia;
    private String fecha_nacimiento;
    private String nivel_riesgo;
    
    @JsonProperty("is_admin")
    private Boolean is_admin;

    public UsuarioRegistrationDTO() {
    }

    public UsuarioRegistrationDTO(String nombre, String correo, String contrasenia, String fecha_nacimiento, Boolean is_admin) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.fecha_nacimiento = fecha_nacimiento;
        this.is_admin = is_admin;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Boolean Is_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    
}