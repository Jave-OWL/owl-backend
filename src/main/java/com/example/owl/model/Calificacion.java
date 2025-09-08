package com.example.owl.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float calificacion;
    private Date fechaUltimaCalificacion;
    private String entidadCalificadora;

    public Calificacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaUltimaCalificacion() {
        return fechaUltimaCalificacion;
    }

    public void setFechaUltimaCalificacion(Date fechaUltimaCalificacion) {
        this.fechaUltimaCalificacion = fechaUltimaCalificacion;
    }

    public String getEntidadCalificadora() {
        return entidadCalificadora;
    }

    public void setEntidadCalificadora(String entidadCalificadora) {
        this.entidadCalificadora = entidadCalificadora;
    }
}
