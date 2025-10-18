package com.example.owl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.owl.DTO.JwtAuthenticationResponse;
import com.example.owl.DTO.LoginDTO;
import com.example.owl.DTO.UsuarioRegistrationDTO;
import com.example.owl.service.AuthenticationService;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody UsuarioRegistrationDTO request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

}
