package com.example.owl.model;

import com.example.owl.model.Fic;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String correo;
    private String contrasena;
    private Boolean es_admin;
    private String nivel_riesgo;

    @ManyToMany
    @JoinTable(
        name = "usuario_fic_recomendada", // tabla intermedia
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "fic_id")
    )
        private List<Fic> fics_recomendados;

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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEs_admin() {
        return es_admin;
    }

    public void setEs_admin(Boolean es_admin) {
        this.es_admin = es_admin;
    }

    public String getNivel_riesgo() {
        return nivel_riesgo;
    }

    public void setNivel_riesgo(String nivel_riesgo) {
        this.nivel_riesgo = nivel_riesgo;
    }

    public List<Fic> getFics_recomendados() {
        return fics_recomendados;
    }

    public void setFics_recomendados(List<Fic> fics_recomendados) {
        this.fics_recomendados = fics_recomendados;
    }

        



}
