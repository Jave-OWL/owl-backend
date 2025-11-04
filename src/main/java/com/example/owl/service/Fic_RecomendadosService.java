package com.example.owl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.owl.model.Fic;
import com.example.owl.model.Fic_Recomendado;
import com.example.owl.model.Usuario;
import com.example.owl.repository.FicRepository;
import com.example.owl.repository.Fic_RecomendadosRepository;
import com.example.owl.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class Fic_RecomendadosService {
     @Autowired
    private FicRepository ficRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Fic_RecomendadosRepository ficRecomendadoRepository;

    @Transactional
public void asignarFicsRecomendados(Usuario usuario) {
    Usuario managedUsuario = usuarioRepository.findById(usuario.getId())
        .orElseThrow();

    ficRecomendadoRepository.deleteByUsuario(managedUsuario);
    ficRecomendadoRepository.flush();

    List<Fic> ficsSeleccionados = new ArrayList<>();

    String perfil = managedUsuario.getNivel_riesgo().toLowerCase();

    switch (perfil) {
        case "conservador":
            ficsSeleccionados = ficRepository.findByTipoIgnoreCase("Renta Fija");
            break;
        case "moderado":
            ficsSeleccionados = ficRepository.findByTipoIgnoreCase("Renta Mixta");
            break;
        case "arriesgado":
            ficsSeleccionados = ficRepository.findByTipoInIgnoreCase(
                    List.of("Alternativa", "Renta Variable"));
            break;
        default:
            return;
    }

    for (Fic fic : ficsSeleccionados) {
        Fic_Recomendado relacion = new Fic_Recomendado(managedUsuario, fic);
        ficRecomendadoRepository.save(relacion);
    }
}


    public List<Fic> getFicsRecomendadosPorUsuario(Usuario usuario) {
        List<Fic_Recomendado> recomendaciones = ficRecomendadoRepository.findAll();
        List<Fic> ficsRecomendados = new ArrayList<>();

        for (Fic_Recomendado recomendacion : recomendaciones) {
            if (recomendacion.getUsuario().getId().equals(usuario.getId())) {
                ficsRecomendados.add(recomendacion.getFic());
            }
        }

        return ficsRecomendados;
    }


}
