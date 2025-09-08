package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RentabilidadVolatilidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoIndicador;
    private String tipoParticipacion;
    private Double ultimoMes;
    private Double ultimos3Meses;
    private Double ultimos6Meses;
    private Double ultimos12Meses;
    private Double ultimos2Anios;
    private Double ultimos3Anios;

    public RentabilidadVolatilidad() {
    }

    public RentabilidadVolatilidad(Long id, String tipoIndicador, String tipoParticipacion, Double ultimoMes, Double ultimos3Meses, Double ultimos6Meses, Double ultimos12Meses, Double ultimos2Anios, Double ultimos3Anios) {
        this.id = id;
        this.tipoIndicador = tipoIndicador;
        this.tipoParticipacion = tipoParticipacion;
        this.ultimoMes = ultimoMes;
        this.ultimos3Meses = ultimos3Meses;
        this.ultimos6Meses = ultimos6Meses;
        this.ultimos12Meses = ultimos12Meses;
        this.ultimos2Anios = ultimos2Anios;
        this.ultimos3Anios = ultimos3Anios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIndicador() {
        return tipoIndicador;
    }

    public void setTipoIndicador(String tipoIndicador) {
        this.tipoIndicador = tipoIndicador;
    }

    public String getTipoParticipacion() {
        return tipoParticipacion;
    }

    public void setTipoParticipacion(String tipoParticipacion) {
        this.tipoParticipacion = tipoParticipacion;
    }

    public Double getUltimoMes() {
        return ultimoMes;
    }

    public void setUltimoMes(Double ultimoMes) {
        this.ultimoMes = ultimoMes;
    }

    public Double getUltimos3Meses() {
        return ultimos3Meses;
    }

    public void setUltimos3Meses(Double ultimos3Meses) {
        this.ultimos3Meses = ultimos3Meses;
    }

    public Double getUltimos6Meses() {
        return ultimos6Meses;
    }

    public void setUltimos6Meses(Double ultimos6Meses) {
        this.ultimos6Meses = ultimos6Meses;
    }

    public Double getUltimos12Meses() {
        return ultimos12Meses;
    }

    public void setUltimos12Meses(Double ultimos12Meses) {
        this.ultimos12Meses = ultimos12Meses;
    }

    public Double getUltimos2Anios() {
        return ultimos2Anios;
    }

    public void setUltimos2Anios(Double ultimos2Anios) {
        this.ultimos2Anios = ultimos2Anios;
    }

    public Double getUltimos3Anios() {
        return ultimos3Anios;
    }

    public void setUltimos3Anios(Double ultimos3Anios) {
        this.ultimos3Anios = ultimos3Anios;
    }

}
