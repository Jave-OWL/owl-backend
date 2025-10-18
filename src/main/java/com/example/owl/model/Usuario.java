package com.example.owl.model;

import com.example.owl.model.Fic;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "is_admin")
    private Boolean is_admin;

    @Column(name = "nivel_riesgo")
    private String nivel_riesgo;

    @OneToMany(mappedBy = "usuario")
    private List<Fic_Recomendado> fics_recomendados;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String contrasenia, Boolean is_admin, String nivel_riesgo) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.is_admin = is_admin;
        this.nivel_riesgo = nivel_riesgo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getNivel_riesgo() {
        return nivel_riesgo;
    }

    public void setNivel_riesgo(String nivel_riesgo) {
        this.nivel_riesgo = nivel_riesgo;
    }


    
    

    // public List<Fic> getFics_recomendados() {
    //     return fics_recomendados;
    // }

    // public void setFics_recomendados(List<Fic> fics_recomendados) {
    //     this.fics_recomendados = fics_recomendados;
    // }

        



}
