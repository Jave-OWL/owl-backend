package com.example.owl.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "fic")
@Entity
public class Fic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_fic")
    private String nombre_fic;

    @Column(name = "gestor")
    private String gestor;

    @Column(name = "custodio")
    private String custodio;

    @Column(name = "fecha_corte")
    private String fecha_corte;

    @Column(name = "politica_de_inversion")
    private String politica_de_inversion;

    @Column(name = "tipo")
    private String tipo;

    //@Column(name = "url")
    //private String url;

    @OneToMany(mappedBy = "fic")
    private List<Fic_Recomendado> usuarios_recomendados;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Calificacion> calificaciones;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Caracteristicas> caracteristicas;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JsonManagedReference
     private List<Composicion_Portafolio> composicion_portafolios;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JsonManagedReference
     private List<Volatilidad_Historica> volatilidad_historicas;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JsonManagedReference
     private List<Rentabilidad_Historica> rentabilidad_historicas;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JsonManagedReference
     private List<Principales_Inversiones> principales_inversiones;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JsonManagedReference
     private List<Plazo_Duracion> plazo_duraciones;

    public Fic() {

    }

    public Fic(Long id, String nombre_fic, String gestor, String custodio, String fecha_corte, String politica_de_inversion, String nivel_riesgo,
               List<Usuario> usuarios_recomendados, List<Calificacion> calificaciones, List<Caracteristicas> caracteristicas,
               List<Composicion_Portafolio> composicion_portafolios, List<Volatilidad_Historica> volatilidad_historicas,
               List<Rentabilidad_Historica> rentabilidad_historicas, List<Principales_Inversiones> principales_inversiones,
               List<Plazo_Duracion> plazo_duraciones, String tipo, String url) {
        this.id = id;
        this.nombre_fic = nombre_fic;
        this.gestor = gestor;
        this.custodio = custodio;
        this.fecha_corte = fecha_corte;
        this.politica_de_inversion = politica_de_inversion;
        // this.nivel_riesgo = nivel_riesgo;
        // this.usuarios_recomendados = usuarios_recomendados;
        this.calificaciones = calificaciones;
        this.caracteristicas = caracteristicas;
        this.composicion_portafolios = composicion_portafolios;
        this.volatilidad_historicas = volatilidad_historicas;
        this.rentabilidad_historicas = rentabilidad_historicas;
        this.principales_inversiones = principales_inversiones;
        this.plazo_duraciones = plazo_duraciones;
        this.tipo = tipo;
       // this.url = url;
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

    // public List<Usuario> getUsuariosRecomendados() {
    //     return usuarios_recomendados;
    // }

    // public void setUsuariosRecomendados(List<Usuario> usuarios_recomendados) {
    //     this.usuarios_recomendados = usuarios_recomendados;
    // }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<Caracteristicas> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristicas> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Composicion_Portafolio> getComposicion_portafolios() {
        return composicion_portafolios;
    }

    public void setComposicion_portafolios(List<Composicion_Portafolio> composicion_portafolios) {
        this.composicion_portafolios = composicion_portafolios;
    }

    public List<Volatilidad_Historica> getVolatilidad_historicas() {
        return volatilidad_historicas;
    }

    public void setVolatilidad_historicas(List<Volatilidad_Historica> volatilidad_historicas) {
        this.volatilidad_historicas = volatilidad_historicas;
    }

    public List<Rentabilidad_Historica> getRentabilidad_historicas() {
        return rentabilidad_historicas;
    }

    public void setRentabilidad_historicas(List<Rentabilidad_Historica> rentabilidad_historicas) {
        this.rentabilidad_historicas = rentabilidad_historicas;
    }

    public List<Principales_Inversiones> getPrincipales_inversiones() {
        return principales_inversiones;
    }

    public void setPrincipales_inversiones(List<Principales_Inversiones> principales_inversiones) {
        this.principales_inversiones = principales_inversiones;
    }

    public List<Plazo_Duracion> getPlazo_duraciones() {
        return plazo_duraciones;
    }

    public void setPlazo_duraciones(List<Plazo_Duracion> plazo_duraciones) {
        this.plazo_duraciones = plazo_duraciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // public String getUrl() {
    //     return url;
    // }

    // public void setUrl(String url) {
    //     this.url = url;
    // }





    
    
}
