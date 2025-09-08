package com.example.owl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.owl.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
