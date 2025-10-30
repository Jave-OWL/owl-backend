package com.example.owl.DTO;

public class UsuarioEditDTO {
    private String nombre;
    private String correo;
    private String fecha_nacimiento;

    // Constructor vacío
    public UsuarioEditDTO() {
    }

    // Constructor con parámetros
    public UsuarioEditDTO(String nombre, String correo, String fecha_nacimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // Getters y Setters
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

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
