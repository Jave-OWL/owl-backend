package com.example.owl.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Fic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String logo;

    private String nombre;

    private String gestor;
    
    private String custodio;

    private Date fechaCorte;

    private String politicaInversion;

    private String link;

    private String riesgo;

    private String rentabilidad;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "fic_id")
    private List<Calificacion> calificaciones;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "fic_id")
    private List<Composicion> composiciones;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "fic_id")
    private List<RentabilidadVolatilidad> rentabilidadVolatilidades;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "fic_id")
    private List<PrincipalesInversiones> principalesInversiones;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "fic_id")
    private List<PlazoDuracion> plazoDuraciones;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn (name = "fic_id")
    private List<Caracteristicas> caracteristicas;




    
    public Fic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getPoliticaInversion() {
        return politicaInversion;
    }

    public void setPoliticaInversion(String politicaInversion) {
        this.politicaInversion = politicaInversion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getRentabilidad() {
        return rentabilidad;
    }

    public void setRentabilidad(String rentabilidad) {
        this.rentabilidad = rentabilidad;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<Composicion> getComposiciones() {
        return composiciones;
    }

    public void setComposiciones(List<Composicion> composiciones) {
        this.composiciones = composiciones;
    }

    public List<RentabilidadVolatilidad> getRentabilidadVolatilidades() {
        return rentabilidadVolatilidades;
    }

    public void setRentabilidadVolatilidades(List<RentabilidadVolatilidad> rentabilidadVolatilidades) {
        this.rentabilidadVolatilidades = rentabilidadVolatilidades;
    }

    public List<PrincipalesInversiones> getPrincipalesInversiones() {
        return principalesInversiones;
    }

    public void setPrincipalesInversiones(List<PrincipalesInversiones> principalesInversiones) {
        this.principalesInversiones = principalesInversiones;
    }

    public List<PlazoDuracion> getPlazoDuraciones() {
        return plazoDuraciones;
    }

    public void setPlazoDuraciones(List<PlazoDuracion> plazoDuraciones) {
        this.plazoDuraciones = plazoDuraciones;
    }

    public List<Caracteristicas> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristicas> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    

 
    
}
