package com.example.owl.controller;

import java.util.List;
import java.util.Optional;

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
    public List<Fic> mostrarFics() {
        return ficService.searchAll();
    }

    @GetMapping("/{id}")
    public Optional<Fic> buscarFicPorId(@PathVariable Long id) {
        return ficService.searchById(id);
    }

    @PostMapping
    public Fic crearFic(@RequestBody Fic fic) {
        return ficService.createFic(fic);
    }

    @DeleteMapping("/{id}")
    public void eliminarFic(@PathVariable Long id) {
        ficService.delete(id);
    }

}
