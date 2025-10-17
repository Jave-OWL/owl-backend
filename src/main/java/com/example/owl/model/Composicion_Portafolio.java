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
@Table(name = "composicion_portafolio")
public class Composicion_Portafolio {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "fic_id")
   @JsonBackReference
   private Fic fic;

   @Column(name = "tipo_composicion")
   private String tipo_composicion;

   @Column(name = "categoria")
   private String categoria;

   @Column(name = "participacion")
   private Float participacion;

   
   public Composicion_Portafolio() {

   }

   public Composicion_Portafolio(String tipo_composicion, String categoria, Float participacion, Fic fic) {
        this.tipo_composicion = tipo_composicion;
        this.categoria = categoria;
        this.participacion = participacion;
        this.fic = fic;
    }

   public Long getId() {
    return id;
   }

   public void setId(Long id) {
    this.id = id;
   }

   public String getTipo_composicion() {
    return tipo_composicion;
   }

   public void setTipo_composicion(String tipo_composicion) {
    this.tipo_composicion = tipo_composicion;
   }

   public String getCategoria() {
    return categoria;
   }

   public void setCategoria(String categoria) {
    this.categoria = categoria;
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
