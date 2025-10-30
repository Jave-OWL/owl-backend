package com.example.owl.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.owl.DTO.PasswordChangeDTO;
import com.example.owl.DTO.UsuarioEditDTO;
import com.example.owl.model.Usuario;
import com.example.owl.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"https://owl.bridgecare.com.co", "http://localhost:4200"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 🔹 Obtener todos los usuarios (solo ADMIN)
    
    @GetMapping("/list")
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // 🔹 Obtener un usuario por ID (solo ADMIN)
  
    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id).orElse(null);
    }

    // 🔹 Crear un nuevo usuario (solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    // 🔹 Actualizar un usuario (solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }

    // 🔹 Eliminar un usuario por ID (solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    // 🔹 Obtener usuario autenticado (cualquier usuario logueado)
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public Usuario obtenerUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // correo o username del token JWT
        return usuarioService.findByCorreo(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
    }

    // 🔹 Editar información del usuario autenticado (nombre, correo, fecha_nacimiento)
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/me")
    public ResponseEntity<?> editarUsuarioActual(@RequestBody UsuarioEditDTO usuarioEditDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioService.findByCorreo(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        
        // Verificar si el correo cambió
        boolean correoChanged = usuarioEditDTO.getCorreo() != null && 
                                !usuarioEditDTO.getCorreo().trim().equalsIgnoreCase(usuario.getCorreo().trim());
        
        Usuario usuarioActualizado = usuarioService.updateUsuarioInfo(
            usuario.getId(),
            usuarioEditDTO.getNombre(),
            usuarioEditDTO.getCorreo(),
            usuarioEditDTO.getFecha_nacimiento()
        );
        
        // Si el correo cambió, indicar al frontend que debe re-autenticarse
        if (correoChanged) {
            return ResponseEntity.ok()
                .header("X-Auth-Required", "true")
                .body(Map.of(
                    "usuario", usuarioActualizado,
                    "requiresReAuth", true,
                    "message", "Correo actualizado. Por favor, inicie sesión nuevamente."
                ));
        }
        
        return ResponseEntity.ok(usuarioActualizado);
    }

    // 🔹 Cambiar contraseña del usuario autenticado
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/me/password")
    public void cambiarContrasenia(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioService.findByCorreo(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        
        usuarioService.updatePassword(usuario.getId(), passwordChangeDTO.getContrasenia());
    }

    
}
