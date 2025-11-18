package com.example.owl.service;

import java.text.Normalizer;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.owl.model.Fic;
import com.example.owl.model.Fic_Recomendado;
import com.example.owl.model.Plazo_Duracion;
import com.example.owl.model.Usuario;
import com.example.owl.repository.FicRepository;
import com.example.owl.repository.Fic_RecomendadosRepository;

import jakarta.transaction.Transactional;

@Service
public class Fic_RecomendadosService {

    @Autowired
    private FicRepository ficRepository;

    @Autowired
    private Fic_RecomendadosRepository ficRecomendadoRepository;


    // Verifica si dos descripciones de plazo son equivalentes
    private boolean esPlazoEquivalente(String texto1, String texto2) {
        int[] rango1 = convertirARangoDias(normalizarTexto(texto1));
        int[] rango2 = convertirARangoDias(normalizarTexto(texto2));

        if (rango1 == null || rango2 == null) return false;

        return rango1[0] <= rango2[1] && rango1[1] >= rango2[0];
    }

    // Convierte una descripción de plazo en un rango de días
    private int[] convertirARangoDias(String texto) {
        if (texto == null || texto.isBlank()) return null;

        texto = normalizarTexto(texto);

        if (texto.contains("redencion") || texto.contains("plazo promedio") ||
                texto.contains("plazo maximo") || texto.contains("s anos"))
            return null;

        if (texto.contains("corto") || texto.contains("180 dias")) return new int[]{1, 180};
        if (texto.contains("mediano")) return new int[]{181, 1095};
        if (texto.contains("largo")) return new int[]{1095, Integer.MAX_VALUE};

        if (texto.contains("1 a 180") || texto.contains("1-180")) return new int[]{1, 180};
        if (texto.contains("181 a 365") || texto.contains("180 a 365") || texto.contains("181-360"))
            return new int[]{181, 365};
        if (texto.contains("1 a 3") || texto.contains("1-3")) return new int[]{365, 1095};
        if (texto.contains("3 a 5") || texto.contains("3-5")) return new int[]{1095, 1825};
        if (texto.contains("mas de 5") || texto.contains("5 anos") || texto.contains("mas de s"))
            return new int[]{1826, Integer.MAX_VALUE};

        return null; 
    }

    // Normaliza el nombre de un Fic para comparaciones
    private String normalizarNombreFic(String nombre) {
        if (nombre == null) return "";
        nombre = nombre.toLowerCase().trim();
        nombre = Normalizer.normalize(nombre, Normalizer.Form.NFD);
        nombre = nombre.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        nombre = nombre.replaceAll("[^a-z0-9 ]", "");
        nombre = nombre.replaceAll("\\s+", " ");
        return nombre;
    }

    // Normaliza un texto para comparaciones
    private String normalizarTexto(String texto) {
        if (texto == null) return "";
        texto = texto.toLowerCase(Locale.ROOT).trim();
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        texto = texto.replaceAll("[^a-z0-9 ]", "");
        texto = texto.replaceAll("\\s+", " ");
        return texto;
    }


    // asignar Fics recomendados a un usuario según su perfil y preferencias
    @Transactional
    public void asignarFicsRecomendados(Usuario usuario, String pactoFront, List<String> rangoDiasFront) {

        ficRecomendadoRepository.deleteByUsuario(usuario);
        ficRecomendadoRepository.flush();
        String perfil = usuario.getNivel_riesgo().toLowerCase();

        Map<String, List<String>> tiposPorPerfil = Map.of(
                "conservador", List.of("Renta Fija"),
                "moderado", List.of("Renta Mixta"),
                "arriesgado", List.of("Alternativa", "Renta Variable")
        );

        List<String> tiposPermitidos = tiposPorPerfil.getOrDefault(perfil, List.of());
        if (tiposPermitidos.isEmpty()) {
            System.out.println("Perfil no reconocido: " + perfil);
            return;
        }

        List<Fic> ficsSeleccionados = ficRepository.findByTipoInIgnoreCase(tiposPermitidos);

       
        Map<String, Fic> mapaPorNombreNormalizado = new LinkedHashMap<>();
        for (Fic fic : ficsSeleccionados) {
            String claveNombre = normalizarNombreFic(fic.getNombre_fic());
            mapaPorNombreNormalizado.putIfAbsent(claveNombre, fic); 
        }
        List<Fic> ficsUnicos = new ArrayList<>(mapaPorNombreNormalizado.values());

      
        String pactoNormalizado = normalizarTexto(pactoFront);
        List<String> rangosNormalizados = (rangoDiasFront == null)
                ? List.of()
                : rangoDiasFront.stream()
                        .filter(Objects::nonNull)
                        .map(this::normalizarTexto)
                        .toList();
                
        System.out.println("\nPerfil: " + perfil + " — Tipos buscados: " + tiposPermitidos);
        System.out.println("Pacto recibido: " + pactoFront);
        System.out.println("Pacto normalizado: " + pactoNormalizado);
        System.out.println("Rangos de días recibidos: " + rangoDiasFront);
        System.out.println("Rangos de días normalizados: " + rangosNormalizados);
        System.out.println("---------------------------------------------------------");

        int contador = 0;

        
        Set<String> ficsGuardadosPorNombre = new HashSet<>();

      
        for (Fic fic : ficsUnicos) {

            String nombreNormalizado = normalizarNombreFic(fic.getNombre_fic());
            if (!ficsGuardadosPorNombre.add(nombreNormalizado)) {
    
                continue;
            }

            for (Plazo_Duracion plazoDuracion : fic.getPlazo_duraciones()) {
                String plazoNormalizado = normalizarTexto(plazoDuracion.getPlazo());

                boolean coincideConPacto = esPlazoEquivalente(pactoNormalizado, plazoNormalizado);
                boolean coincideConRango = rangosNormalizados.stream()
                        .anyMatch(rango -> esPlazoEquivalente(rango, plazoNormalizado));

                if (coincideConPacto || coincideConRango) {
                    System.out.println("Coincidencia encontrada en FIC: " + fic.getNombre_fic());
                    System.out.println("Tipo de FIC: " + fic.getTipo());
                    System.out.println("Plazo coincidente: " + plazoDuracion.getPlazo());
                    System.out.println("Perfil del usuario: " + usuario.getNivel_riesgo());
                    System.out.println("---------------------------------------------------------");

                    Fic_Recomendado relacion = new Fic_Recomendado(usuario, fic);
                    ficRecomendadoRepository.save(relacion);

                    contador++;
                    break; 
                }
            }
        }

        System.out.println("Total de FICs recomendados al usuario " + usuario.getCorreo() + ": " + contador);
    }



    // Obtener la lista de Fics recomendados para un usuario específico
   
    public List<Fic> getFicsRecomendadosPorUsuario(Usuario usuario) {
        List<Fic_Recomendado> recomendaciones = ficRecomendadoRepository.findAll();
        List<Fic> ficsRecomendados = new ArrayList<>();
        Set<Long> idsFic = new HashSet<>();

        for (Fic_Recomendado recomendacion : recomendaciones) {
            if (recomendacion.getUsuario() != null &&
                recomendacion.getUsuario().getId() != null &&
                recomendacion.getUsuario().getId().equals(usuario.getId())) {

                Fic fic = recomendacion.getFic();
                if (fic != null && fic.getId() != null && idsFic.add(fic.getId())) {
                    // solo se añade si el id no estaba ya en el set
                    ficsRecomendados.add(fic);
                }
            }
        }

        return ficsRecomendados;
    }
}
