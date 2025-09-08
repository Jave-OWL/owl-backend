package com.example.owl.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlazoDuracion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String inversionesPorPlazo;
    private int participacion;
    
    public PlazoDuracion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInversionesPorPlazo() {
        return inversionesPorPlazo;
    }

    public void setInversionesPorPlazo(String inversionesPorPlazo) {
        this.inversionesPorPlazo = inversionesPorPlazo;
    }

    public int getParticipacion() {
        return participacion;
    }

    public void setParticipacion(int participacion) {
        this.participacion = participacion;
    }
}
