/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pruebafernandocarranza.controller;

import com.example.pruebafernandocarranza.model.User;
import com.example.pruebafernandocarranza.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fer
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail());
        Optional<User> userOptional = userService.getByEmail(loginRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                // Aquí podrías manejar la creación de la sesión si es necesario
                return ResponseEntity.ok(new ResponseMessage("Inicio de sesión exitoso"));
            }
        }

        return ResponseEntity.badRequest().body(new ResponseMessage("Credenciales inválidas"));
    }

    // Método para cerrar sesión
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            // Aquí deberías manejar el cierre de sesión
            return ResponseEntity.ok(new ResponseMessage("Sesión cerrada exitosamente"));
        }
        return ResponseEntity.badRequest().body(new ResponseMessage("No hay sesión iniciada"));
    }

    // Método para verificar si hay una sesión iniciada
    @GetMapping("/session")
    public ResponseEntity<?> checkSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(new ResponseMessage("Sesión activa"));
        }
        return ResponseEntity.ok(new ResponseMessage("No hay sesión activa"));
    }

    // Clase interna para manejar las respuestas
    public static class ResponseMessage {

        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    // Clase interna para manejar la solicitud de inicio de sesión
    public static class LoginRequest {

        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
