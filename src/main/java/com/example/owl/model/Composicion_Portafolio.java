package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Composicion_Portafolio {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String tipo_composicion;
   private String categoria;
   private float participacion;

   @ManyToOne
   @JoinColumn(name = "fic_id")
   private Fic fic;
   
   public Composicion_Portafolio() {

   }

   public Composicion_Portafolio(String tipo_composicion, String categoria, float participacion, Fic fic) {
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
