package com.example.owl.DTO;

import java.time.LocalDate;

public class FicDTO {
    private Long id;
    private String nombre_fic;
    private String gestor;
    private String custodio;
    private String fecha_corte;            // Spring Boot parsea ISO yyyy-MM-dd
    private String politica_de_inversion;
   // private String nivel_riesgo;              // si tu entidad lo maneja
    private String tipo;   
    
    
    // opcional si existe en la entidad

    public FicDTO(Long id, String nombre_fic, String gestor, String custodio, String fecha_corte,
                  String politica_de_inversion, String tipo) {
        this.id = id;
        this.nombre_fic = nombre_fic;
        this.gestor = gestor;
        this.custodio = custodio;
        this.fecha_corte = fecha_corte;
        this.politica_de_inversion = politica_de_inversion;
        //this.nivel_riesgo = nivel_riesgo;
        this.tipo = tipo;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_fic() {
        return nombre_fic;
    }

    public void setNombre_fic(String nombre_fic) {
        this.nombre_fic = nombre_fic;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getCustodio() {
        return custodio;
    }

    public void setCustodio(String custodio) {
        this.custodio = custodio;
    }

    public String getFecha_corte() {
        return fecha_corte;
    }

    public void setFecha_corte(String fecha_corte) {
        this.fecha_corte = fecha_corte;
    }

    public String getPolitica_de_inversion() {
        return politica_de_inversion;
    }

    public void setPolitica_de_inversion(String politica_de_inversion) {
        this.politica_de_inversion = politica_de_inversion;
    }

    // public String getNivel_riesgo() {
    //     return nivel_riesgo;
    // }

    // public void setNivel_riesgo(String nivel_riesgo) {
    //     this.nivel_riesgo = nivel_riesgo;
    // }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    


}
