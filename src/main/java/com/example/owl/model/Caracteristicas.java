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
@Table(name = "caracteristicas")
public class Caracteristicas {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "fic_id")
   @JsonBackReference
   private Fic fic;

   @Column(name = "tipo")
   private String tipo;

   @Column(name = "valor")
   private Float valor;

   @Column(name = "fecha_inicio_operaciones")
   private String fecha_inicio_operaciones;
   
   @Column(name = "no_unidades_en_circulacion")
   private Float no_unidades_en_circulacion;

   


   public Caracteristicas() {
   }

   public Caracteristicas(String tipo, Float valor, String fecha_inicio_operaciones,
         Float no_unidades_en_circulacion, Fic fic) {
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

   public Float getValor() {
    return valor;
   }

   public void setValor(Float valor) {
    this.valor = valor;
   }

   public String getFecha_inicio_operaciones() {
    return fecha_inicio_operaciones;
   }

   public void setFecha_inicio_operaciones(String fecha_inicio_operaciones) {
    this.fecha_inicio_operaciones = fecha_inicio_operaciones;
   }

   public Float getNo_unidades_en_circulacion() {
    return no_unidades_en_circulacion;
   }

   public void setNo_unidades_en_circulacion(Float no_unidades_en_circulacion) {
    this.no_unidades_en_circulacion = no_unidades_en_circulacion;
   }

   public Fic getFic() {
    return fic;
   }

   public void setFic(Fic fic) {
    this.fic = fic;
   }

   
}
