package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Principales_Inversores {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String emisor;
    private float participacion;

    @ManyToOne
    @JoinColumn(name = "fic_id")
    private Fic fic;

    public Principales_Inversores() {

    }

    public Principales_Inversores(String emisor, float participacion, Fic fic) {
        this.emisor = emisor;
        this.participacion = participacion;
        this.fic = fic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
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
