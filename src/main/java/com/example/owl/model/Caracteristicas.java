package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Caracteristicas {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String tipo;
   private float valor;
   private String fecha_inicio_operaciones;
   private float no_unidades_en_circulacion;
   
   @ManyToOne
   @JoinColumn(name = "fic_id")
   private Fic fic;


   public Caracteristicas() {
   }

   public Caracteristicas(String tipo, float valor, String fecha_inicio_operaciones,
         float no_unidades_en_circulacion, Fic fic) {
      this.tipo = tipo;
      this.valor = valor;
      this.fecha_inicio_operaciones = fecha_inicio_operaciones;
      this.no_unidades_en_circulacion = no_unidades_en_circulacion;
      this.fic = fic;
   }

   public Long getId() {
    return id;
   }

   public void setId(Long id) {
    this.id = id;
   }

   public String getTipo() {
    return tipo;
   }

   public void setTipo(String tipo) {
    this.tipo = tipo;
   }

   public float getValor() {
    return valor;
   }

   public void setValor(float valor) {
    this.valor = valor;
   }

   public String getFecha_inicio_operaciones() {
    return fecha_inicio_operaciones;
   }

   public void setFecha_inicio_operaciones(String fecha_inicio_operaciones) {
    this.fecha_inicio_operaciones = fecha_inicio_operaciones;
   }

   public float getNo_unidades_en_circulacion() {
    return no_unidades_en_circulacion;
   }

   public void setNo_unidades_en_circulacion(float no_unidades_en_circulacion) {
    this.no_unidades_en_circulacion = no_unidades_en_circulacion;
   }

   public Fic getFic() {
    return fic;
   }

   public void setFic(Fic fic) {
    this.fic = fic;
   }

   
}
