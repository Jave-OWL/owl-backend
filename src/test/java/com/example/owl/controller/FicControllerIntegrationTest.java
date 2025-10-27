package com.example.owl.controller;

import com.example.owl.DTO.LoginDTO;
import com.example.owl.DTO.JwtAuthenticationResponse;
import com.example.owl.model.Fic;
import com.example.owl.model.Usuario;
import com.example.owl.repository.Fic_RecomendadosRepository;
import com.example.owl.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
public class FicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;

    @Autowired
    private Fic_RecomendadosRepository ficRecomendadoRepository;

    @BeforeEach
    void setUp() throws Exception {
        ficRecomendadoRepository.deleteAll(); // 🔹 primero borra dependencias
        usuarioRepository.deleteAll();   
        // Crear usuario admin
        Usuario admin = new Usuario();
        admin.setNombre("Admin Test");
        admin.setCorreo("admin@test.com");
        admin.setContrasenia(passwordEncoder.encode("123456"));
        admin.setFecha_nacimiento("1990-01-01");
        admin.setIs_admin(true);
        usuarioRepository.save(admin);

        // Autenticar y obtener token JWT
        LoginDTO login = new LoginDTO();
        login.setCorreo("admin@test.com");
        login.setContrasenia("123456");

        String response = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JwtAuthenticationResponse jwtResponse =
                objectMapper.readValue(response, JwtAuthenticationResponse.class);
        jwtToken = jwtResponse.getToken();
    }

    @Test
    void testCrearFic_ComoAdmin_DeberiaRetornarFicCreado() throws Exception {
        Fic fic = new Fic();
        fic.setNombre_fic("FONDO NUEVO");
        fic.setGestor("FIDUCIARIA DE OCCIDENTE S.A.");
        fic.setCustodio("CITI TRUST COLOMBIA S.A.");
        fic.setFecha_corte("2025-10-18");
        fic.setPolitica_de_inversion("Portafolio enfocado en renta fija con estrategias de bajo riesgo y liquidez inmediata.");
        fic.setTipo("Renta Fija");
        fic.setCalificaciones(new ArrayList<>());
        fic.setCaracteristicas(new ArrayList<>());
        fic.setComposicion_portafolios(new ArrayList<>());
        fic.setVolatilidad_historicas(new ArrayList<>());
        fic.setRentabilidad_historicas(new ArrayList<>());
        fic.setPrincipales_inversiones(new ArrayList<>());
        fic.setPlazo_duraciones(new ArrayList<>());

        mockMvc.perform(post("/fic/create")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fic)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_fic").value("FONDO NUEVO"))
                .andExpect(jsonPath("$.gestor").value("FIDUCIARIA DE OCCIDENTE S.A."));
    }

    @Test
    void testObtenerFics_ComoAdmin_DeberiaRetornarLista() throws Exception {
        mockMvc.perform(get("/fic/list")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testObtenerFicPorId_NoExistente_DeberiaRetornar404() throws Exception {
        mockMvc.perform(get("/fic/9999")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isNotFound());
    }

    @Test
    void testActualizarFic_ComoAdmin_DeberiaActualizarDatos() throws Exception {
        // Crear FIC inicial
        Fic fic = new Fic();
        fic.setNombre_fic("FIC Original");
        fic.setGestor("Gestor 1");
        fic.setCustodio("Custodio 1");
        fic.setFecha_corte("2025-10-18");
        fic.setPolitica_de_inversion("Renta fija básica");

        fic.setTipo("Renta Fija");
        fic.setCalificaciones(new ArrayList<>());
        fic.setCaracteristicas(new ArrayList<>());
        fic.setComposicion_portafolios(new ArrayList<>());
        fic.setVolatilidad_historicas(new ArrayList<>());
        fic.setRentabilidad_historicas(new ArrayList<>());
        fic.setPrincipales_inversiones(new ArrayList<>());
        fic.setPlazo_duraciones(new ArrayList<>());

        String ficJson = mockMvc.perform(post("/fic/create")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fic)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Fic creado = objectMapper.readValue(ficJson, Fic.class);

        // Actualizarlo
        creado.setTipo("Renta Fija");
        creado.setPolitica_de_inversion("Nueva política de inversión agresiva.");

        mockMvc.perform(put("/fic/update")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creado)))
                .andExpect(status().isOk())
    .andExpect(jsonPath("$.politica_de_inversion").value("Nueva política de inversión agresiva."));
    }

    @Test
    void testEliminarFic_ComoAdmin_DeberiaEliminarFic() throws Exception {
        // Crear FIC primero
        Fic fic = new Fic();
        fic.setNombre_fic("Fondo para eliminar");
        fic.setGestor("Gestor Test");
        fic.setCustodio("Custodio Test");
        fic.setFecha_corte("2025-10-18");
        fic.setPolitica_de_inversion("Temporal");
    
        fic.setTipo("Renta Fija");
        fic.setCalificaciones(new ArrayList<>());
        fic.setCaracteristicas(new ArrayList<>());
        fic.setComposicion_portafolios(new ArrayList<>());
        fic.setVolatilidad_historicas(new ArrayList<>());
        fic.setRentabilidad_historicas(new ArrayList<>());
        fic.setPrincipales_inversiones(new ArrayList<>());
        fic.setPlazo_duraciones(new ArrayList<>());

        String ficJson = mockMvc.perform(post("/fic/create")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fic)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Fic creado = objectMapper.readValue(ficJson, Fic.class);

        mockMvc.perform(delete("/fic/" + creado.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk());
    }
}
