package com.example.owl.service;


import com.example.owl.DTO.JwtAuthenticationResponse;
import com.example.owl.DTO.LoginDTO;
import com.example.owl.DTO.UsuarioRegistrationDTO;
import com.example.owl.model.Usuario;
import com.example.owl.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    /**
     * Registro de nuevo usuario
     */
    public JwtAuthenticationResponse signup(UsuarioRegistrationDTO request) {
        if (request.getCorreo() == null || request.getContrasenia() == null) {
            throw new IllegalArgumentException("Correo y contraseña son obligatorios.");
        }
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        // Si manejas fecha_nacimiento en la entidad y DTO, descomenta:
        // usuario.setFecha_nacimiento(request.getFecha_nacimiento());
        usuario.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
        usuario.setIs_admin(request.Is_admin());
        // Si tu DTO trae esto y tu entidad lo tiene:
        // usuario.setIs_admin(Boolean.TRUE.equals(request.getIsAdmin()));
        // usuario.setNivel_riesgo(request.getNivelRiesgo());

        usuarioRepository.save(usuario);

        String jwt = jwtService.generateToken(usuario.getCorreo());

            return new JwtAuthenticationResponse(
                jwt,
                usuario.getCorreo(),
                Boolean.TRUE.equals(usuario.getIs_admin())
        );
    }

    /**
     * Inicio de sesión
     */
    public JwtAuthenticationResponse login(LoginDTO request) {
        if (request.getcorreo() == null || request.getcontrasenia() == null) {
            throw new IllegalArgumentException("Correo y contraseña son obligatorios.");
        }

        try {
            if (authenticationManager != null) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getcorreo(),
                                request.getcontrasenia()
                        )
                );
            } else {
                // Validación manual si no tienes AuthenticationManager configurado
                Usuario probe = usuarioRepository.findByCorreo(request.getcorreo())
                        .orElseThrow(() -> new IllegalArgumentException("Correo o contraseña incorrectos."));
                if (!passwordEncoder.matches(request.getcontrasenia(), probe.getContrasenia())) {
                    throw new IllegalArgumentException("Correo o contraseña incorrectos.");
                }
            }
        } catch (AuthenticationException e) {
            log.warn("Error de autenticación: {}", e.getMessage());
            throw new IllegalArgumentException("Correo o contraseña incorrectos.");
        }

        Usuario usuario = usuarioRepository.findByCorreo(request.getcorreo())
                .orElseThrow(() -> new IllegalArgumentException("Correo o contraseña incorrectos."));

        String jwt = jwtService.generateToken(usuario.getCorreo());

           return new JwtAuthenticationResponse(
                jwt,
                usuario.getCorreo(),
                Boolean.TRUE.equals(usuario.getIs_admin())
        );
    }
}
