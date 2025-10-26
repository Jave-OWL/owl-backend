package com.example.owl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.owl.model.Fic;

@Repository
public interface FicRepository extends JpaRepository<Fic, Long> {

    List<Fic> findByTipoIgnoreCase(String tipo);

    List<Fic> findByTipoInIgnoreCase(List<String> tipos);
}
