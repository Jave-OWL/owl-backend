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


public Optional<Fic> searchById(Long id) {
    return ficRepository.findById(id);
}

public List<Fic> searchAll(){
    return ficRepository.findAll();
}

public Fic createFic(Fic fic) {
    return ficRepository.save(fic);
}

public java.util.Optional<Fic> update(Long id, Fic ficDetails) {
    return ficRepository.findById(id).map(fic -> {
  
        return ficRepository.save(fic);
    });
}

public boolean delete(Long id) {
    if (ficRepository.existsById(id)) {
        ficRepository.deleteById(id);
        return true;
    }
    return false;
}
}
