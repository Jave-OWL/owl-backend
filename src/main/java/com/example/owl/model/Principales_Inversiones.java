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

@Entity
@Table (name = "principales_inversiones")
public class Principales_Inversiones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fic_id")
    @JsonBackReference
    private Fic fic;

    @Column(name = "emisor")
    private String emisor;

    @Column(name = "participacion")
    private Float participacion;

 

    public Principales_Inversiones() {

    }

    public Principales_Inversiones(String emisor, Float participacion, Fic fic) {
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

    public Float getParticipacion() {
        return participacion;
    }

    public void setParticipacion(Float participacion) {
        this.participacion = participacion;
    }

    public Fic getFic() {
        return fic;
    }

    public void setFic(Fic fic) {
        this.fic = fic;
    }

    
}
