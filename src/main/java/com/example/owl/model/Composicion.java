package com.example.owl.model;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Composicion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float calificacion;
    private String tipoRenta;
    private String paisEmisor;
    private String moneda;
    private String activo;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "composicion_id")
    private List<ValorComposicion> valoresComposiciones;


    public Composicion() {
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

    public String getTipoRenta() {
        return tipoRenta;
    }

    public void setTipoRenta(String tipoRenta) {
        this.tipoRenta = tipoRenta;
    }

    public String getPaisEmisor() {
        return paisEmisor;
    }

    public void setPaisEmisor(String paisEmisor) {
        this.paisEmisor = paisEmisor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public List<ValorComposicion> getValoresComposiciones() {
        return valoresComposiciones;
    }

    public void setValoresComposiciones(List<ValorComposicion> valoresComposiciones) {
        this.valoresComposiciones = valoresComposiciones;
    }

    
}
