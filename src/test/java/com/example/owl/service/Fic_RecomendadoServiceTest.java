package com.example.owl.service;

import com.example.owl.model.Fic;
import com.example.owl.model.Fic_Recomendado;
import com.example.owl.model.Usuario;
import com.example.owl.repository.FicRepository;
import com.example.owl.repository.Fic_RecomendadosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Fic_RecomendadoServiceTest {

    @Mock private FicRepository ficRepository;
    @Mock private Fic_RecomendadosRepository ficRecomendadoRepository;

    @InjectMocks private Fic_RecomendadosService service;

    private Usuario uConservador;
    private Usuario uModerado;
    private Usuario uArriesgado;
    private Usuario uOtro;

    private Fic rf; // renta fija
    private Fic rm; // renta mixta
    private Fic rv; // renta variable
    private Fic alt; // alternativa

    @Captor private ArgumentCaptor<Fic_Recomendado> ficRecCaptor;

    @BeforeEach
    void setUp() {
        uConservador = new Usuario("UserC","c@owl.com","hash", false,"1990-01-01","Conservador");
        uConservador.setId(1L);
        uModerado = new Usuario("UserM","m@owl.com","hash", false,"1990-01-01","Moderado");
        uModerado.setId(2L);
        uArriesgado = new Usuario("UserA","a@owl.com","hash", false,"1990-01-01","Arriesgado");
        uArriesgado.setId(3L);
        uOtro = new Usuario("UserX","x@owl.com","hash", false,"1990-01-01","Desconocido");
        uOtro.setId(4L);

        rf = new Fic(); rf.setId(10L); rf.setTipo("Renta Fija");
        rm = new Fic(); rm.setId(20L); rm.setTipo("Renta Mixta");
        rv = new Fic(); rv.setId(30L); rv.setTipo("Renta Variable");
        alt = new Fic(); alt.setId(40L); alt.setTipo("Alternativa");
    }

    @Test
    void asignarFicsRecomendados_conservador_ok() {
        when(ficRepository.findByTipoIgnoreCase("Renta Fija"))
                .thenReturn(List.of(rf));

        service.asignarFicsRecomendados(uConservador);

        verify(ficRecomendadoRepository).deleteByUsuario(uConservador);
        verify(ficRecomendadoRepository, atLeastOnce()).save(ficRecCaptor.capture());

        List<Fic_Recomendado> guardados = ficRecCaptor.getAllValues();
        assertThat(guardados).hasSize(1);
        assertThat(guardados.get(0).getUsuario().getId()).isEqualTo(1L);
        assertThat(guardados.get(0).getFic().getId()).isEqualTo(10L);
        verify(ficRepository).findByTipoIgnoreCase("Renta Fija");
        verifyNoMoreInteractions(ficRepository);
    }

    @Test
    void asignarFicsRecomendados_moderado_ok() {
        when(ficRepository.findByTipoIgnoreCase("Renta Mixta"))
                .thenReturn(List.of(rm));

        service.asignarFicsRecomendados(uModerado);

        verify(ficRecomendadoRepository).deleteByUsuario(uModerado);
        verify(ficRecomendadoRepository, atLeastOnce()).save(ficRecCaptor.capture());

        Fic_Recomendado fr = ficRecCaptor.getValue();
        assertThat(fr.getUsuario().getId()).isEqualTo(2L);
        assertThat(fr.getFic().getId()).isEqualTo(20L);
        verify(ficRepository).findByTipoIgnoreCase("Renta Mixta");
        verifyNoMoreInteractions(ficRepository);
    }

    @Test
    void asignarFicsRecomendados_arriesgado_ok() {
        when(ficRepository.findByTipoInIgnoreCase(List.of("Alternativa", "Renta Variable")))
                .thenReturn(List.of(alt, rv));

        service.asignarFicsRecomendados(uArriesgado);

        verify(ficRecomendadoRepository).deleteByUsuario(uArriesgado);
        verify(ficRecomendadoRepository, atLeastOnce()).save(ficRecCaptor.capture());

        List<Fic_Recomendado> guardados = ficRecCaptor.getAllValues();
        assertThat(guardados).hasSize(2);
        assertThat(guardados).extracting(fr -> fr.getFic().getId())
                .containsExactlyInAnyOrder(40L, 30L);

        verify(ficRepository).findByTipoInIgnoreCase(List.of("Alternativa", "Renta Variable"));
        verifyNoMoreInteractions(ficRepository);
    }


    @Test
    void getFicsRecomendadosPorUsuario_filtraPorUsuario() {
        // Simula que hay relaciones para varios usuarios
        Fic_Recomendado r1 = new Fic_Recomendado(uConservador, rf);
        Fic_Recomendado r2 = new Fic_Recomendado(uModerado, rm);
        Fic_Recomendado r3 = new Fic_Recomendado(uConservador, rv);

        when(ficRecomendadoRepository.findAll()).thenReturn(List.of(r1, r2, r3));

        List<Fic> res = service.getFicsRecomendadosPorUsuario(uConservador);

        assertThat(res).extracting(Fic::getId).containsExactlyInAnyOrder(10L, 30L);
        // No llama a ficRepository en este método
        verifyNoInteractions(ficRepository);
        verify(ficRecomendadoRepository).findAll();
    }

    @Test
    void getFicsRecomendadosPorUsuario_listaVacia() {
        when(ficRecomendadoRepository.findAll()).thenReturn(new ArrayList<>());

        List<Fic> res = service.getFicsRecomendadosPorUsuario(uModerado);

        assertThat(res).isEmpty();
        verify(ficRecomendadoRepository).findAll();
        verifyNoInteractions(ficRepository);
    }
}
