package com.example.owl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table (name = "plazo_duracion")
@Entity
public class Plazo_Duracion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "plazo")
    private String plazo;

    @Column(name = "participacion")
    private float participacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fic_id")
    @JsonBackReference
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
