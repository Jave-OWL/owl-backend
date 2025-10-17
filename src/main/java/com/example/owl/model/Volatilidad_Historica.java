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
@Table(name = "volatilidad_historica")
public class Volatilidad_Historica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fic_id")
    @JsonBackReference
    private Fic fic;

    @Column(name = "tipo_participacion")
    private String tipo_de_participacion;

    @Column(name="anio_corrido")
    private Float anio_corrido;

    @Column(name = "ultimo_mes")
    private Float ultimo_mes;

    @Column(name = "ultimos_6_meses")
    private Float ultimo_6_meses;

    @Column(name = "ultimo_anio")
    private Float ultimo_anio;

    @Column(name = "ultimos_2_anios")
    private Float ultimo_2_anios;

    @Column(name = "ultimos_3_anios")
    private Float ultimo_3_anios;

    public Volatilidad_Historica() {
        
    }

    public Volatilidad_Historica(String tipo_de_participacion, float ultimo_mes, float ultimo_6_meses,
            float anio_corrido, float ultimo_anio, float ultimo_2_anios, float ultimo_3_anios, Fic fic) {
        this.tipo_de_participacion = tipo_de_participacion;
        this.ultimo_mes = ultimo_mes;
        this.ultimo_6_meses = ultimo_6_meses;
        this.anio_corrido = anio_corrido;
        this.ultimo_anio = ultimo_anio;
        this.ultimo_2_anios = ultimo_2_anios;
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

    public float getUltimo_mes() {
        return ultimo_mes;
    }

    public void setUltimo_mes(float ultimo_mes) {
        this.ultimo_mes = ultimo_mes;
    }

    public float getUltimo_6_meses() {
        return ultimo_6_meses;
    }

    public void setUltimo_6_meses(float ultimo_6_meses) {
        this.ultimo_6_meses = ultimo_6_meses;
    }

    public float getAnio_corrido() {
        return anio_corrido;
    }

    public void setAnio_corrido(float anio_corrido) {
        this.anio_corrido = anio_corrido;
    }

    public Float getUltimo_anio() {
        return ultimo_anio;
    }

    public void setUltimo_anio(float ultimo_anio) {
        this.ultimo_anio = ultimo_anio;
    }

    public Float getUltimo_2_anios() {
        return ultimo_2_anios;
    }

    public void setUltimo_2_anios(float ultimo_2_anios) {
        this.ultimo_2_anios = ultimo_2_anios;
    }

    public Float getUltimo_3_anios() {
        return ultimo_3_anios;
    }

    public void setUltimo_3_anios(float ultimo_3_anios) {
        this.ultimo_3_anios = ultimo_3_anios;
    }

    public Fic getFic() {
        return fic;
    }

    public void setFic(Fic fic) {
        this.fic = fic;
    }

}
