package com.example.owl.controller;

import com.example.owl.DTO.JwtAuthenticationResponse;
import com.example.owl.DTO.LoginDTO;
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

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
public class UsuarioControllerIntegrationTest {

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
        // 🔹 primero limpiar tabla hija
        ficRecomendadoRepository.deleteAll();

        // 🔹 luego limpiar usuarios
        usuarioRepository.deleteAll();

        // Crear usuario admin
        Usuario admin = new Usuario();
        admin.setNombre("Admin Test");
        admin.setCorreo("admin@test.com");
        admin.setContrasenia(passwordEncoder.encode("123456"));
        admin.setFecha_nacimiento("1990-01-01");
        admin.setIs_admin(true);
        usuarioRepository.save(admin);

        // Login para obtener JWT
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

        JwtAuthenticationResponse jwtResponse = objectMapper.readValue(response, JwtAuthenticationResponse.class);
        jwtToken = jwtResponse.getToken();
    }

    @Test
    void testCrearUsuario_ComoAdmin_DeberiaRetornarUsuarioCreado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario Prueba");
        usuario.setCorreo("usuario@test.com");
        usuario.setContrasenia("123456");
        usuario.setFecha_nacimiento("1995-05-05");
        usuario.setIs_admin(false);

        mockMvc.perform(post("/usuario")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Usuario Prueba"))
                .andExpect(jsonPath("$.correo").value("usuario@test.com"))
                .andExpect(jsonPath("$.is_admin").value(false));
    }

    @Test
    void testObtenerUsuarios_ComoAdmin_DeberiaRetornarLista() throws Exception {
        mockMvc.perform(get("/usuario/list")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    void testObtenerUsuarioPorId_ComoAdmin_DeberiaRetornarUsuario() throws Exception {
        Usuario user = new Usuario();
        user.setNombre("Usuario ID");
        user.setCorreo("usuarioid@test.com");
        user.setContrasenia(passwordEncoder.encode("123456"));
        user.setFecha_nacimiento("1992-02-02");
        user.setIs_admin(false);
        Usuario guardado = usuarioRepository.save(user);

        mockMvc.perform(get("/usuario/" + guardado.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Usuario ID"));
    }

    @Test
    void testActualizarUsuario_ComoAdmin_DeberiaActualizarDatos() throws Exception {
        Usuario user = new Usuario();
        user.setNombre("Usuario Update");
        user.setCorreo("usuarioupdate@test.com");
        user.setContrasenia(passwordEncoder.encode("123456"));
        user.setFecha_nacimiento("1993-03-03");
        user.setIs_admin(false);
        Usuario guardado = usuarioRepository.save(user);

        guardado.setNombre("Usuario Actualizado");

        mockMvc.perform(put("/usuario/update")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(guardado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Usuario Actualizado"));
    }

    @Test
    void testEliminarUsuario_ComoAdmin_DeberiaEliminarUsuario() throws Exception {
        Usuario user = new Usuario();
        user.setNombre("Usuario Eliminar");
        user.setCorreo("usuarioeliminar@test.com");
        user.setContrasenia(passwordEncoder.encode("123456"));
        user.setFecha_nacimiento("1994-04-04");
        user.setIs_admin(false);
        Usuario guardado = usuarioRepository.save(user);

        mockMvc.perform(delete("/usuario/" + guardado.getId())
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk());
    }

    @Test
    void testObtenerUsuarioActual_DeberiaRetornarUsuarioLogueado() throws Exception {
        mockMvc.perform(get("/usuario/me")
                        .header("Authorization", "Bearer " + jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correo").value("admin@test.com"))
                .andExpect(jsonPath("$.is_admin").value(true));
    }
}
