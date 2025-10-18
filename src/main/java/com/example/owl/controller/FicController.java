package com.example.owl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.owl.model.Fic;
import com.example.owl.service.FicService;

@RestController
@RequestMapping("/fic")
@CrossOrigin(origins = "http://localhost:4200")
public class FicController {

    @Autowired
    private FicService ficService;

    // Obtener lista de FICs
    @GetMapping("/list")
    public List<Fic> obtenerFics() {
        return ficService.getAllFics();
    }

    // Obtener FIC por ID
    @GetMapping("/{id}")
    public ResponseEntity<Fic> obtenerFicPorId(@PathVariable("id") long id) {
        return ficService.getFicById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo FIC
    @PostMapping("/create")
    public Fic crearFic(@RequestBody Fic fic) {
        return ficService.saveFic(fic);
    }

    // Eliminar FIC por ID
    @DeleteMapping("/{id}")
    public void eliminarFic(@PathVariable Long id) {
        ficService.deleteFic(id);
    }

    // Actualizar FIC
    @PutMapping("/update")
    public Fic actualizarFic(@RequestBody Fic fic) {
        return ficService.updateFic(fic);
    }
}
