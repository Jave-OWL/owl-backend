package com.example.owl.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "fic_recomendado")
public class Fic_Recomendado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fic_id")
    private Fic fic;


    public Fic_Recomendado() {
    }

    public Fic_Recomendado(Usuario usuario, Fic fic) {
        this.usuario = usuario;
        this.fic = fic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fic getFic() {
        return fic;
    }

    public void setFic(Fic fic) {
        this.fic = fic;
    }

    
}
