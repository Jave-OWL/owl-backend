package com.example.owl.controller;

import com.example.owl.DTO.JwtAuthenticationResponse;
import com.example.owl.DTO.LoginDTO;
import com.example.owl.DTO.UsuarioRegistrationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("integration-test")
@TestPropertySource(properties = {
  "spring.datasource.url=jdbc:h2:mem:owltest;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH",
  "spring.datasource.username=sa",
  "spring.datasource.password=",
  "spring.datasource.driver-class-name=org.h2.Driver",
  "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
  "spring.jpa.hibernate.ddl-auto=create-drop",
  "spring.jpa.generate-ddl=true",
  "spring.jpa.defer-datasource-initialization=true"
})
public class AuthenticationControllerIntegrationTest {

    @Autowired private TestRestTemplate rest;

    @Test
    void signupAndLogin_ok() {
        UsuarioRegistrationDTO reg = new UsuarioRegistrationDTO();
        reg.setNombre("Tester");
        reg.setCorreo("tester@example.com");
        reg.setContrasenia("secret123");
        reg.setFecha_nacimiento("2000-01-01");
        

        ResponseEntity<JwtAuthenticationResponse> signResp =
                rest.postForEntity("http://localhost:8081/auth/signup", reg, JwtAuthenticationResponse.class);
        assertEquals(200, signResp.getStatusCodeValue());
        assertNotNull(signResp.getBody());
        assertNotNull(signResp.getBody().getToken());

        LoginDTO login = new LoginDTO();
        login.setCorreo("tester@example.com");
        login.setContrasenia("secret123");

        ResponseEntity<JwtAuthenticationResponse> logResp =
                rest.postForEntity("http://localhost:8081/auth/login", login, JwtAuthenticationResponse.class);
        assertEquals(200, logResp.getStatusCodeValue());
        assertNotNull(logResp.getBody());
        assertNotNull(logResp.getBody().getToken());
    }
}
