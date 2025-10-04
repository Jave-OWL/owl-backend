package com.example.owl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.owl.model.Fic;
import com.example.owl.service.FicService;

@RestController
@RequestMapping("/fic")
@CrossOrigin(origins = "http://localhost:4200")
public class FicController {

    @Autowired
    private FicService ficService;

    @GetMapping("/list")
    public List<Fic> obtenerFics() {
        return ficService.getAllFics();
    }

    @GetMapping("/{id}")
    public Fic obtenerFicPorId(Long id) {
        return ficService.getFicById(id).orElse(null);
    }

    @PostMapping("/create")
    public Fic crearFic(@RequestBody Fic fic) {
        return ficService.saveFic(fic);
    }

    @DeleteMapping("/{id}")
    public void eliminarFic(@PathVariable Long id) {
        ficService.deleteFic(id);
    }
    
    
}
