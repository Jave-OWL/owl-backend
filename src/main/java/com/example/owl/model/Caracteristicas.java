package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Caracteristicas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String calificacion;
    private String tipoDeRenta;
    private String sectorEconomico;
    private String paisEmisor;
    private String moneda;
    private String activo;

    public Caracteristicas() {
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
    public String getTipoDeRenta() {
        return tipoDeRenta;
    }
    public void setTipoDeRenta(String tipoDeRenta) {
        this.tipoDeRenta = tipoDeRenta;
    }
    public String getSectorEconomico() {
        return sectorEconomico;
    }
    public void setSectorEconomico(String sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
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

}
