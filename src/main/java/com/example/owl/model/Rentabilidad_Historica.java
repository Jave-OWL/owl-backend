package com.example.owl.model;

import org.hibernate.annotations.ManyToAny;

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
@Table(name = "rentabilidad_historica")
public class Rentabilidad_Historica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo_participacion")
    private String tipo_de_participacion;

    @Column(name = "ultimo_mes")
    private Float ultimo_mes;   

    @Column(name = "ultimos_6_meses")
    private Float ultimos_6_meses;

    @Column(name = "anio_corrido")
    private Float anio_corrido;

    @Column(name = "ultimo_anio")
    private Float ultimo_anio;

    @Column(name = "ultimos_2_anios")
    private Float ultimos_2_anios;

    @Column(name = "ultimos_3_anios")
    private Float ultimo_3_anios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fic_id")
    @JsonBackReference
    private Fic fic;
    
    public Rentabilidad_Historica() {

    }

    public Rentabilidad_Historica(String tipo_de_participacion, Float ultimo_mes, Float ultimo_6_meses,
            Float anio_corrido, Float ultimo_anio, Float ultimo_2_anios, Float ultimo_3_anios, Fic fic) {
        this.tipo_de_participacion = tipo_de_participacion;
        this.ultimo_mes = ultimo_mes;
        this.ultimos_6_meses = ultimo_6_meses;
        this.anio_corrido = anio_corrido;
        this.ultimo_anio = ultimo_anio;
        this.ultimos_2_anios = ultimo_2_anios;
        this.ultimo_3_anios = ultimo_3_anios;
        this.fic = fic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_de_participacion() {
        return tipo_de_participacion;
    }

    public void setTipo_de_participacion(String tipo_de_participacion) {
        this.tipo_de_participacion = tipo_de_participacion;
    }

    public Float getUltimo_mes() {
        return ultimo_mes;
    }

    public void setUltimo_mes(Float ultimo_mes) {
        this.ultimo_mes = ultimo_mes;
    }

    public Float getUltimo_6_meses() {
        return ultimos_6_meses;
    }

    public void setUltimo_6_meses(Float ultimo_6_meses) {
        this.ultimos_6_meses = ultimo_6_meses;
    }

    public Float getAnio_corrido() {
        return anio_corrido;
    }

    public void setAnio_corrido(Float anio_corrido) {
        this.anio_corrido = anio_corrido;
    }

    public Float getUltimo_anio() {
        return ultimo_anio;
    }

    public void setUltimo_anio(Float ultimo_anio) {
        this.ultimo_anio = ultimo_anio;
    }

    public Float getUltimo_2_anios() {
        return ultimos_2_anios;
    }

    public void setUltimo_2_anios(Float ultimo_2_anios) {
        this.ultimos_2_anios = ultimo_2_anios;
    }

    public Float getUltimo_3_anios() {
        return ultimo_3_anios;
    }

    public void setUltimo_3_anios(Float ultimo_3_anios) {
        this.ultimo_3_anios = ultimo_3_anios;
    }

    public Fic getFic() {
        return fic;
    }

    public void setFic(Fic fic) {
        this.fic = fic;
    }

    

}
