package com.example.owl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


@PreAuthorize("isAuthenticated()")
@GetMapping("/me")
public Usuario obtenerUsuarioActual() {
    // Obtener el usuario autenticado
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName(); // normalmente es el correo o username del token JWT

    // Buscar el usuario en la base de datos
    return usuarioService.findByCorreo(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
}

}
