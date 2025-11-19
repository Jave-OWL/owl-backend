package com.example.owl.controller;

import com.example.owl.DTO.JwtAuthenticationResponse;
import com.example.owl.DTO.LoginDTO;
import com.example.owl.DTO.UsuarioRegistrationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("integration-test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PrediccionControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:8081/prediccion";

    @Autowired private WebTestClient webTestClient;
    @Autowired private TestRestTemplate rest;

    private String userToken;

    @BeforeEach
    void setup() {
        // crear usuario
        UsuarioRegistrationDTO reg = new UsuarioRegistrationDTO();
        reg.setNombre("Miguel");
        reg.setCorreo("miguel@owl.com");
        reg.setContrasenia("miguel123");
        reg.setIs_admin(false);
        reg.setFecha_nacimiento("2003-09-29");
        rest.postForEntity("http://localhost:8081/auth/signup", reg, Object.class);

        // login
        LoginDTO login = new LoginDTO();
        login.setCorreo("miguel@owl.com");
        login.setContrasenia("miguel123");
        userToken = rest.postForEntity("http://localhost:8081/auth/login", login, JwtAuthenticationResponse.class)
                .getBody().getToken();
    }

    @Test
    void postPrediccion_y_listarPorPerfil_ok() {
        Map<String,String> body = Map.of(
                "perfilRiesgo", "Conservador",
                "rangoDias", "1-180",
                "pactoPermanencia", "SI"
        );

        webTestClient.post()
            .uri(BASE_URL)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
            .bodyValue(body)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.message").isEqualTo("Perfil de riesgo actualizado correctamente");

        webTestClient.get()
            .uri(BASE_URL + "/list")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$").exists(); 
    }
}
