package com.example.owl.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    @Entity
    public class Usuario {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nombre;
        private String correo;
        private String contrasenia;
        private String rol;

        public Usuario() {
        }

        public Usuario(String nombre, String correo, String contrasenia, String rol) {
            this.nombre = nombre;
            this.correo = correo;
            this.contrasenia = contrasenia;
            this.rol = rol;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getContrasenia() {
            return contrasenia;
        }

        public void setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }
    }

