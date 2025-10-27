package com.example.owl.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import com.example.owl.model.Fic;
import com.example.owl.model.Fic_Recomendado;
import com.example.owl.model.Usuario;

@DataJpaTest
@TestPropertySource(properties = {
  "spring.jpa.hibernate.ddl-auto=create-drop"
})
class Fic_RecomendadoRepositoryTest {

  @Autowired 
  private Fic_RecomendadosRepository ficRecRepo;

  @Autowired 
  private FicRepository ficRepository;
  
  @Autowired 
  private UsuarioRepository usuarioRepository;

  private Usuario user;
  private Fic fic1;
  private Fic fic2;

  @BeforeEach
  void setUp() {
    user = usuarioRepository.save(new Usuario("User","user@owl.com","pass", false, "1992-02-02", "Moderado"));

    fic1 = new Fic();
    fic1.setNombre_fic("FIC 1");
    fic1.setGestor("G1");
    fic1.setCustodio("C1");
    fic1.setFecha_corte("2025-10-01");
    fic1.setPolitica_de_inversion("Politica 1");
    fic1.setTipo("Renta Fija");
    fic1.setUrl("http://1");
    fic1 = ficRepository.save(fic1);

    fic2 = new Fic();
    fic2.setNombre_fic("FIC 2");
    fic2.setGestor("G2");
    fic2.setCustodio("C2");
    fic2.setFecha_corte("2025-10-01");
    fic2.setPolitica_de_inversion("Politica 2");
    fic2.setTipo("Renta Variable");
    fic2.setUrl("http://2");
    fic2 = ficRepository.save(fic2);

    ficRecRepo.save(new Fic_Recomendado(user, fic1));
    ficRecRepo.save(new Fic_Recomendado(user, fic2));
  }

  @Test
  void saveAndDeleteByUsuario_ok() {
    List<Fic_Recomendado> allBefore = ficRecRepo.findAll();
    assertThat(allBefore.stream().filter(fr -> fr.getUsuario().getId().equals(user.getId()))).hasSize(2);

    ficRecRepo.deleteByUsuario(user);

    List<Fic_Recomendado> allAfter = ficRecRepo.findAll();
    assertThat(allAfter.stream().filter(fr -> fr.getUsuario().getId().equals(user.getId()))).isEmpty();
  }
}
