package com.example.owl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.owl.model.Fic;
import com.example.owl.repository.FicRepository;

@Service
public class FicService {
    
    @Autowired 
    private FicRepository ficRepository;

    public Optional<Fic> getFicById(Long id) {
        return ficRepository.findById(id);
    }

    public List<Fic> getAllFics() {
        return ficRepository.findAll();
    }

    public Fic saveFic(Fic fic) {
        return ficRepository.save(fic);
    }

    public void deleteFic(Long id) {
        ficRepository.deleteById(id);
    }

    public Fic updateFic(Fic fic) {
        if (ficRepository.existsById(fic.getId())) {
            return ficRepository.save(fic);
        } else {
            throw new RuntimeException("Fic no encontrado con id: " + fic.getId());
        }
    }


    public List<Fic> getFicsByNivelRiesgo(String nivelRiesgo) {
        return ficRepository.findByTipoIgnoreCase(nivelRiesgo);
    }

    
}
