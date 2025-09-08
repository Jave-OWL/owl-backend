package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PrincipalesInversiones {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String emisor;
private String participacionDelEmisor;


public PrincipalesInversiones() {
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

public String getParticipacionDelEmisor() {
    return participacionDelEmisor;
}

public void setParticipacionDelEmisor(String participacionDelEmisor) {
    this.participacionDelEmisor = participacionDelEmisor;
}

}
