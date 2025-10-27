package com.example.owl.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import com.example.owl.model.Fic;

@DataJpaTest
@TestPropertySource(properties = {
  "spring.jpa.hibernate.ddl-auto=create-drop"
})
class FicRepositoryTest {

  @Autowired
  private FicRepository ficRepository;

  @BeforeEach
  void setUp() {
    Fic f1 = new Fic();
    f1.setNombre_fic("Alpha RF");
    f1.setGestor("Gestor A");
    f1.setCustodio("Custodio A");
    f1.setFecha_corte("2025-10-01");
    f1.setPolitica_de_inversion("Politica corta A");
    f1.setTipo("Renta Fija");
    f1.setUrl("http://a");
    ficRepository.save(f1);

    Fic f2 = new Fic();
    f2.setNombre_fic("Beta RV");
    f2.setGestor("Gestor B");
    f2.setCustodio("Custodio B");
    f2.setFecha_corte("2025-10-01");
    f2.setPolitica_de_inversion("Politica corta B");
    f2.setTipo("Renta Variable");
    f2.setUrl("http://b");
    ficRepository.save(f2);

    // Mismo tipo con otra capitalización para probar IgnoreCase
    Fic f3 = new Fic();
    f3.setNombre_fic("Gamma rf");
    f3.setGestor("Gestor C");
    f3.setCustodio("Custodio C");
    f3.setFecha_corte("2025-10-01");
    f3.setPolitica_de_inversion("Politica corta C");
    f3.setTipo("renta fija");
    f3.setUrl("http://c");
    ficRepository.save(f3);
  }

  @Test
  void saveAndFindAll_ok() {
    List<Fic> all = ficRepository.findAll();
    assertThat(all).hasSize(3);
  }

  @Test
  void findByTipoIgnoreCase_ok() {
    List<Fic> rentaFija = ficRepository.findByTipoIgnoreCase("RENTA FIJA");
    assertThat(rentaFija).hasSize(2);
    assertThat(rentaFija).extracting(Fic::getTipo).allMatch(t -> t.equalsIgnoreCase("renta fija"));
  }

  @Test
  void findByTipoInIgnoreCase_ok() {
    List<Fic> result = ficRepository.findByTipoInIgnoreCase(
        Arrays.asList("RENTA FIJA", "renta variable"));
    assertThat(result).hasSize(3);
  }

  @Test
  void deleteById_thenEmptyOptional() {
    Long id = ficRepository.findAll().get(0).getId();
    ficRepository.deleteById(id);
    assertThat(ficRepository.findById(id)).isEmpty();
  }
}
