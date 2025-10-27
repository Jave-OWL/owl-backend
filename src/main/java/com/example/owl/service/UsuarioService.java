package com.example.owl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.owl.model.Usuario;
import com.example.owl.repository.UsuarioRepository;
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    
    public Usuario updateUsuario(Usuario usuario) {
    if (usuarioRepository.existsById(usuario.getId())) {
        return usuarioRepository.save(usuario);
    } else {
        throw new RuntimeException("Usuario no encontrado con id: " + usuario.getId());
    }
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
