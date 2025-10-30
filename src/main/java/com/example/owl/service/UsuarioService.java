package com.example.owl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.owl.model.Usuario;
import com.example.owl.repository.UsuarioRepository;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

  public Usuario createUsuario(Usuario usuario) {
    if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
        throw new RuntimeException("Usuario ya existe");
    }
    return usuarioRepository.save(usuario);
}

    
    // Actualizar usuario completo (solo ADMIN)
    public Usuario updateUsuario(Usuario usuario) {
    if (usuarioRepository.existsById(usuario.getId())) {
        return usuarioRepository.save(usuario);
    } else {
        throw new RuntimeException("Usuario no encontrado con id: " + usuario.getId());
    }
}

    // Actualizar información básica del usuario (self-edit)
    public Usuario updateUsuarioInfo(Long id, String nombre, String correo, String fechaNacimiento) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        
        // Validar que el correo no esté siendo usado por OTRO usuario
        if (correo != null && !correo.trim().isEmpty()) {
            String correoNormalizado = correo.trim().toLowerCase();
            String correoActualNormalizado = usuario.getCorreo().trim().toLowerCase();
            
            // Solo validar si el correo realmente cambió
            if (!correoNormalizado.equals(correoActualNormalizado)) {
                // Buscar si existe otro usuario con ese correo
                Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(correo);
                if (usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(id)) {
                    throw new RuntimeException("El correo ya está en uso por otro usuario");
                }
            }
        }
        
        if (nombre != null && !nombre.trim().isEmpty()) usuario.setNombre(nombre.trim());
        if (correo != null && !correo.trim().isEmpty()) usuario.setCorreo(correo.trim());
        if (fechaNacimiento != null && !fechaNacimiento.trim().isEmpty()) usuario.setFecha_nacimiento(fechaNacimiento.trim());
        
        return usuarioRepository.save(usuario);
    }

    // Actualizar contraseña del usuario
    public Usuario updatePassword(Long id, String nuevaContrasenia) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        
        // Cifrar la nueva contraseña
        usuario.setContrasenia(passwordEncoder.encode(nuevaContrasenia));
        
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


   public UserDetailsService userDetailsService() {
        return username -> {
            Usuario u = usuarioRepository.findByCorreo(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

            List<SimpleGrantedAuthority> auths = new ArrayList<>();
            auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            if (Boolean.TRUE.equals(u.getIs_admin())) {
                auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }

            // Devuelve un User de Spring Security con contraseña cifrada y authorities
            return new org.springframework.security.core.userdetails.User(
                    u.getCorreo(),
                    u.getContrasenia(),
                    auths
            );
        };
    }
}
