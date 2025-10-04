package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Plazo_Duracion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String plazo;
    private float participacion;

    @ManyToOne
    @JoinColumn(name = "fic_id")
    private Fic fic;

    public Plazo_Duracion() {

    }


    public Plazo_Duracion(String plazo, float participacion, Fic fic) {
        this.plazo = plazo;
        this.participacion = participacion;
        this.fic = fic;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getPlazo() {
        return plazo;
    }


    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }


    public float getParticipacion() {
        return participacion;
    }


    public void setParticipacion(float participacion) {
        this.participacion = participacion;
    }


    public Fic getFic() {
        return fic;
    }


    public void setFic(Fic fic) {
        this.fic = fic;
    }

    
}
