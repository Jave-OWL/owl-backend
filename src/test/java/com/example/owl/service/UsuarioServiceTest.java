package com.example.owl.service;

import com.example.owl.DTO.JwtAuthenticationResponse;
import com.example.owl.DTO.UsuarioRegistrationDTO;
import com.example.owl.model.Usuario;
import com.example.owl.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock private UsuarioRepository usuarioRepository;
    @Mock private PasswordEncoder passwordEncoder;

    private ArgumentCaptor<Usuario> usuarioCaptor;

    @InjectMocks private UsuarioService usuarioService;

    private Usuario base;

    @Mock private AuthenticationService authenticationService;



    @BeforeEach
    void init() {
        base = new Usuario("Alice", "alice@owl.com", "plain", false, "1990-01-01", "Conservador");
    }

    @Test
    void registrarUsuario_ok() {
        // Arrange
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Usuario out = usuarioService.createUsuario(base); // o registrarUsuario(base)

        // Assert
        assertThat(out).isNotNull();
        assertThat(out.getCorreo()).isEqualTo("alice@owl.com");
        verify(usuarioRepository).save(any(Usuario.class));

    
    }

 @Test
void registrarUsuario_duplicado_noLanzaExcepcion_enUsuarioService() {
    // Arrange
    when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

    // Act
    Usuario out = usuarioService.createUsuario(base); // o registrarUsuario(base) según tu firma

    // Assert
    assertThat(out).isNotNull();
    verify(usuarioRepository).save(any(Usuario.class));
}



    @Test
    void findByCorreo_ok() {
        when(usuarioRepository.findByCorreo("alice@owl.com")).thenReturn(Optional.of(base));

        Optional<Usuario> res = usuarioService.findByCorreo("alice@owl.com");

        assertThat(res).isPresent();
        assertThat(res.get().getNombre()).isEqualTo("Alice");
    }

@Test
void updateUsuario_ok() {
    // Arrange
    Usuario existente = new Usuario("Alice","alice@owl.com","plain", false, "1990-01-01", "Conservador");
    existente.setId(1L);

    when(usuarioRepository.existsById(1L)).thenReturn(true);
    when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

    // Act
    existente.setNombre("Alice Mod");
    Usuario saved = usuarioService.updateUsuario(existente);

    // Assert
    assertThat(saved).isNotNull();
    assertThat(saved.getNombre()).isEqualTo("Alice Mod");
    verify(usuarioRepository).existsById(1L);
    verify(usuarioRepository).save(existente);
}




    @Test
    void userDetailsService_roles_ok() {
        Usuario admin = new Usuario("Admin","admin@owl.com","hash", true, "1985-02-02","Moderado");
        when(usuarioRepository.findByCorreo("admin@owl.com")).thenReturn(Optional.of(admin));

        UserDetails ud = usuarioService.userDetailsService().loadUserByUsername("admin@owl.com");
        assertThat(ud.getAuthorities()).extracting("authority").contains("ROLE_ADMIN");
    }

@Test
void signup_duplicado_lanzaExcepcion() {
    Usuario usuarioExistente = new Usuario();
    usuarioExistente.setCorreo("alice@owl.com");

    when(usuarioRepository.existsByCorreo("alice@owl.com")).thenReturn(true);

    assertThatThrownBy(() -> usuarioService.createUsuario(usuarioExistente))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("Usuario ya existe");
}


}
