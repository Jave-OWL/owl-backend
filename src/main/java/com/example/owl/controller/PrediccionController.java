package com.example.owl.controller;

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
@CrossOrigin(origins = {"https://owl.bridgecare.com.co", "http://localhost:4200"})
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
    public ResponseEntity<?> recibirPrediccion(@RequestBody Map<String, String> body) {
        // 1️⃣ Leer los datos enviados desde Angular
        String perfilRiesgo = body.get("perfilRiesgo");
        String rangoDias = body.get("rangoDias");
        String pactoPermanencia = body.get("pactoPermanencia");

        System.out.println("Perfil: " + perfilRiesgo);
        System.out.println("Rango: " + rangoDias);
        System.out.println("Pacto: " + pactoPermanencia);

        // 2️⃣ Obtener el usuario autenticado (por email, username, etc.)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();  // ← normalmente es el correo o username del JWT

        // 3️⃣ Buscar al usuario en la base de datos
        Usuario usuario = usuarioService.findByCorreo(username) 
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

        // 4️⃣ Actualizar el campo perfilRiesgo en su registro
        usuario.setNivel_riesgo(perfilRiesgo);
        usuarioService.updateUsuario(usuario);


        ficRecomendadosService.asignarFicsRecomendados(usuario);
        // 5️⃣ (Opcional) Devolver respuesta al frontend
        return ResponseEntity.ok(Map.of(
                "message", "Perfil de riesgo actualizado correctamente",
                "perfilRiesgo", perfilRiesgo,
                "usuario", usuario.getCorreo()
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