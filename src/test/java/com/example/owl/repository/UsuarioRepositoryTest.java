package com.example.owl.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import com.example.owl.model.Usuario;

@DataJpaTest
@TestPropertySource(properties = {
  "spring.jpa.hibernate.ddl-auto=create-drop"
})
class UsuarioRepositoryTest {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @BeforeEach
  void setUp() {
    Usuario u1 = new Usuario("Alice","alice@owl.com","pass", false, "1990-01-01", "Conservador");
    Usuario u2 = new Usuario("Bob","bob@owl.com","pass", true, "1988-05-05", "Moderado");
    usuarioRepository.save(u1);
    usuarioRepository.save(u2);
  }

  @Test
  void save_findAll_ok() {
    assertThat(usuarioRepository.findAll()).hasSize(2);
  }

  @Test
  void findByCorreo_ok() {
    Optional<Usuario> alice = usuarioRepository.findByCorreo("alice@owl.com");
    assertThat(alice).isPresent();
    assertThat(alice.get().getNombre()).isEqualTo("Alice");
  }

  @Test
  void findByCorreo_notFound() {
    assertThat(usuarioRepository.findByCorreo("no@existe.com")).isNotPresent();
  }

  @Test
  void existsByCorreo_trueFalse() {
    assertThat(usuarioRepository.existsByCorreo("alice@owl.com")).isTrue();
    assertThat(usuarioRepository.existsByCorreo("no@existe.com")).isFalse();
  }

  @Test
  void updateUsuario_ok() {
    Usuario u = usuarioRepository.findByCorreo("alice@owl.com").orElseThrow();
    u.setNombre("Alice Mod");
    Usuario saved = usuarioRepository.save(u);
    assertThat(saved.getNombre()).isEqualTo("Alice Mod");
  }
}
