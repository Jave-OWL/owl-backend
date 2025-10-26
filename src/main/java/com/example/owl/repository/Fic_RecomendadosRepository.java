package com.example.owl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.owl.model.Fic_Recomendado;
import com.example.owl.model.Usuario;

public interface Fic_RecomendadosRepository extends JpaRepository<Fic_Recomendado, Long> {
     void deleteByUsuario(Usuario usuario);
}
