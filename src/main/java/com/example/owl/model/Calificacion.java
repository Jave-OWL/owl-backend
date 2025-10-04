package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String calificacion;
    private String fecha_ultima_calificacion;
    private String entidad_calificadora;
    private Boolean entidad_calificadora_normalizada;

    @ManyToOne
    @JoinColumn(name = "fic_id")
    private Fic fic;

    public Calificacion() {
    }

    public Calificacion(String calificacion, String fecha_ultima_calificacion, String entidad_calificadora,
            Boolean entidad_calificadora_normalizada, Fic fic) {
        this.calificacion = calificacion;
        this.fecha_ultima_calificacion = fecha_ultima_calificacion;
        this.entidad_calificadora = entidad_calificadora;
        this.entidad_calificadora_normalizada = entidad_calificadora_normalizada;
        this.fic = fic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getFecha_ultima_calificacion() {
        return fecha_ultima_calificacion;
    }

    public void setFecha_ultima_calificacion(String fecha_ultima_calificacion) {
        this.fecha_ultima_calificacion = fecha_ultima_calificacion;
    }

    public String getEntidad_calificadora() {
        return entidad_calificadora;
    }

    public void setEntidad_calificadora(String entidad_calificadora) {
        this.entidad_calificadora = entidad_calificadora;
    }

    public Boolean getEntidad_calificadora_normalizada() {
        return entidad_calificadora_normalizada;
    }

    public void setEntidad_calificadora_normalizada(Boolean entidad_calificadora_normalizada) {
        this.entidad_calificadora_normalizada = entidad_calificadora_normalizada;
    }

    public Fic getFic() {
        return fic;
    }

    public void setFic(Fic fic) {
        this.fic = fic;
    }



}
