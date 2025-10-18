package com.example.owl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.owl.model.Usuario;
import com.example.owl.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }


    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}
