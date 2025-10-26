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

@Service
public class Fic_RecomendadosService {
     @Autowired
    private FicRepository ficRepository;

    @Autowired
    private Fic_RecomendadosRepository ficRecomendadoRepository;

    public void asignarFicsRecomendados(Usuario usuario) {

    ficRecomendadoRepository.deleteByUsuario(usuario);

    List<Fic> ficsSeleccionados = new ArrayList<>();

    String perfil = usuario.getNivel_riesgo().toLowerCase();

    switch (perfil) {
        case "conservador":
            ficsSeleccionados = ficRepository.findByTipoIgnoreCase("Renta fija");
            break;
        case "moderado":
            ficsSeleccionados = ficRepository.findByTipoIgnoreCase("Mixto");
            break;
        case "arriesgado":
            ficsSeleccionados = ficRepository.findByTipoInIgnoreCase(
                    List.of("Alternativo", "Variable"));
            break;
        default:
            return;
    }

    for (Fic fic : ficsSeleccionados) {
        Fic_Recomendado relacion = new Fic_Recomendado(usuario, fic);
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
