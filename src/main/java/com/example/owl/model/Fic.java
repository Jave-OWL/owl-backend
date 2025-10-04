package com.example.owl.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

public class Fic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre_fic;
    private String gestor;
    private String custodio;
    private String fecha_corte;
    private String politica_de_inversion;
    private String nivel_riesgo;

    @ManyToMany(mappedBy = "fics_recomendados")
    private List<Usuario> usuarios_recomendados;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL)
    private List<Caracteristicas> caracteristicas;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL)
    private List<Composicion_Portafolio> composicion_portafolios;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL)
    private List<Volatilidad_Historica> volatilidad_historicas;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL)
    private List<Rentabilidad_Historica> rentabilidad_historicas;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL)
    private List<Principales_Inversores> principales_inversores;

    @OneToMany(mappedBy = "fic", cascade = CascadeType.ALL)
    private List<Plazo_Duracion> plazo_duraciones;

    public Fic() {

    }

    public Fic(Long id, String nombre_fic, String gestor, String custodio, String fecha_corte, String politica_de_inversion, String nivel_riesgo,
               List<Usuario> usuarios_recomendados, List<Calificacion> calificaciones, List<Caracteristicas> caracteristicas,
               List<Composicion_Portafolio> composicion_portafolios, List<Volatilidad_Historica> volatilidad_historicas,
               List<Rentabilidad_Historica> rentabilidad_historicas, List<Principales_Inversores> principales_inversores,
               List<Plazo_Duracion> plazo_duraciones) {
        this.id = id;
        this.nombre_fic = nombre_fic;
        this.gestor = gestor;
        this.custodio = custodio;
        this.fecha_corte = fecha_corte;
        this.politica_de_inversion = politica_de_inversion;
        this.nivel_riesgo = nivel_riesgo;
        this.usuarios_recomendados = usuarios_recomendados;
        this.calificaciones = calificaciones;
        this.caracteristicas = caracteristicas;
        this.composicion_portafolios = composicion_portafolios;
        this.volatilidad_historicas = volatilidad_historicas;
        this.rentabilidad_historicas = rentabilidad_historicas;
        this.principales_inversores = principales_inversores;
        this.plazo_duraciones = plazo_duraciones;
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

    public String getNivel_riesgo() {
        return nivel_riesgo;
    }

    public void setNivel_riesgo(String nivel_riesgo) {
        this.nivel_riesgo = nivel_riesgo;
    }

    public List<Usuario> getUsuariosRecomendados() {
        return usuarios_recomendados;
    }

    public void setUsuariosRecomendados(List<Usuario> usuarios_recomendados) {
        this.usuarios_recomendados = usuarios_recomendados;
    }

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

    public List<Principales_Inversores> getPrincipales_inversores() {
        return principales_inversores;
    }

    public void setPrincipales_inversores(List<Principales_Inversores> principales_inversores) {
        this.principales_inversores = principales_inversores;
    }

    public List<Plazo_Duracion> getPlazo_duraciones() {
        return plazo_duraciones;
    }

    public void setPlazo_duraciones(List<Plazo_Duracion> plazo_duraciones) {
        this.plazo_duraciones = plazo_duraciones;
    }






    
    
}
