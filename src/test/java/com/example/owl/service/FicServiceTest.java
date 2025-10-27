package com.example.owl.service;

import com.example.owl.model.Fic;
import com.example.owl.repository.FicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FicServiceTest {

    @Mock
    private FicRepository ficRepository;

    @InjectMocks
    private FicService ficService;

    private Fic fic;

    @BeforeEach
    void setUp() {
        fic = new Fic();
        fic.setId(1L);
        fic.setNombre_fic("FIC PRUEBA");
        fic.setGestor("Gestor X");
        fic.setCustodio("Custodio X");
        fic.setFecha_corte("2025-10-01");
        fic.setPolitica_de_inversion("Politica corta");
        fic.setTipo("Renta Fija");
        fic.setUrl("http://fic");
    }

    @Test
    void getFicById_found() {
        when(ficRepository.findById(1L)).thenReturn(Optional.of(fic));

        Optional<Fic> res = ficService.getFicById(1L);

        assertThat(res).isPresent();
        assertThat(res.get().getNombre_fic()).isEqualTo("FIC PRUEBA");
        verify(ficRepository).findById(1L);
    }

    @Test
    void getFicById_notFound() {
        when(ficRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Fic> res = ficService.getFicById(99L);

        assertThat(res).isEmpty();
        verify(ficRepository).findById(99L);
    }

    @Test
    void getAllFics_ok() {
        when(ficRepository.findAll()).thenReturn(List.of(fic, fic));

        List<Fic> all = ficService.getAllFics();

        assertThat(all).hasSize(2);
        verify(ficRepository).findAll();
    }

    @Test
    void saveFic_ok() {
        when(ficRepository.save(fic)).thenReturn(fic);

        Fic saved = ficService.saveFic(fic);

        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isEqualTo(1L);
        verify(ficRepository).save(fic);
    }

    @Test
    void deleteFic_ok() {
        doNothing().when(ficRepository).deleteById(1L);

        ficService.deleteFic(1L);

        verify(ficRepository).deleteById(1L);
    }

    @Test
    void updateFic_ok() {
        when(ficRepository.existsById(1L)).thenReturn(true);
        fic.setTipo("Renta Variable");
        when(ficRepository.save(fic)).thenReturn(fic);

        Fic updated = ficService.updateFic(fic);

        assertThat(updated.getTipo()).isEqualTo("Renta Variable");
        verify(ficRepository).existsById(1L);
        verify(ficRepository).save(fic);
    }

    @Test
    void updateFic_notFound_throws() {
        Fic noExiste = new Fic();
        noExiste.setId(999L);
        when(ficRepository.existsById(999L)).thenReturn(false);

        assertThatThrownBy(() -> ficService.updateFic(noExiste))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Fic no encontrado con id: 999");

        verify(ficRepository).existsById(999L);
        verify(ficRepository, never()).save(any());
    }

    @Test
    void getFicsByNivelRiesgo_delegatesToRepository() {
        when(ficRepository.findByTipoIgnoreCase("Moderado"))
            .thenReturn(List.of(fic));

        List<Fic> res = ficService.getFicsByNivelRiesgo("Moderado");

        assertThat(res).hasSize(1);
        verify(ficRepository).findByTipoIgnoreCase("Moderado");
    }
}
