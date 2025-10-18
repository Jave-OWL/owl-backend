package com.example.owl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.owl.model.Usuario;
@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
    Optional<Usuario> findByCorreo(String email);

    boolean existsByCorreo(String correo);
}
