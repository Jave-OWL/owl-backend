package com.example.owl.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.owl.model.Fic;
import com.example.owl.model.Usuario;
import com.example.owl.repository.Fic_RecomendadosRepository;
import com.example.owl.repository.UsuarioRepository;
import com.example.owl.service.FicService;
import com.example.owl.service.Fic_RecomendadosService;
import com.example.owl.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/prediccion")
@CrossOrigin(origins = "http://localhost:4200")
public class PrediccionController {

@Autowired 
private UsuarioService usuarioService;

@Autowired
private FicService ficService;

@Autowired
private Fic_RecomendadosService ficRecomendadosService;

@Autowired 
private Fic_RecomendadosRepository ficRecomendadosRepository;

 @PostMapping
public ResponseEntity<?> recibirPrediccion(@RequestBody Map<String, Object> body) {
    // ⿡ Leer los datos enviados desde Angular
    String perfilRiesgo = (String) body.get("perfilRiesgo");

    // ⚡ Leer pacto (único valor)
    String pactoPermanencia = null;
    Object pactoObj = body.get("pactoPermanencia");
    if (pactoObj != null) {
        pactoPermanencia = pactoObj.toString();
    }

    // ⚡ Leer rango de días (puede venir como lista o string)
    Object rangoObj = body.get("rangoDias");
    List<String> rangoDias = new ArrayList<>();
    if (rangoObj instanceof List<?>) {
        ((List<?>) rangoObj).forEach(r -> rangoDias.add(r.toString()));
    } else if (rangoObj instanceof String) {
        rangoDias.add(rangoObj.toString());
    }

    System.out.println("Perfil: " + perfilRiesgo);
    System.out.println("Pacto de permanencia: " + pactoPermanencia);
    System.out.println("Rangos de días: " + rangoDias);
    System.out.println("---------------------------------------------------------");

    // ⿢ Obtener el usuario autenticado
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    // ⿣ Buscar al usuario
    Usuario usuario = usuarioService.findByCorreo(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

    // ⿤ Actualizar perfil de riesgo
    usuario.setNivel_riesgo(perfilRiesgo);
    usuarioService.updateUsuario(usuario);

    // ⿥ Llamar al servicio con el pacto único y la lista de rangos
    ficRecomendadosService.asignarFicsRecomendados(usuario, pactoPermanencia, rangoDias);

    // ⿦ Responder al frontend
    return ResponseEntity.ok(Map.of(
        "message", "Perfil de riesgo actualizado correctamente",
        "perfilRiesgo", perfilRiesgo,
        "usuario", usuario.getCorreo(),
        "pactoPermanencia", pactoPermanencia,
        "rangoDias", rangoDias
    ));
}



  @PreAuthorize("isAuthenticated()")
    @GetMapping("/list")
    public List<Fic> obtenerFics() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Usuario usuario = usuarioService.findByCorreo(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

        
        
                return ficService.getFicsByNivelRiesgo(usuario.getNivel_riesgo());

            }

}