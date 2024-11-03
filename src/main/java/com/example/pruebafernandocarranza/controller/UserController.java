package com.example.pruebafernandocarranza.controller;

import com.example.pruebafernandocarranza.model.User;
import com.example.pruebafernandocarranza.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fer
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> all() {
        return userService.all();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<User> user = userService.getById(id);
        return user.isPresent() 
                ? ResponseEntity.ok(user.get()) 
                : ResponseEntity.status(404).body("{\"message\": \"Usuario no encontrado.\"}");
    }
    
    @PostMapping
    public ResponseEntity<?> store(@RequestBody User user) {
        if (userService.getByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("{\"message\": \"El correo ya está en uso.\"}");
        }
        User userCreated = userService.save(user);
        return ResponseEntity.status(201).body(userCreated);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userService.getById(id);
        
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(404).body("{\"message\": \"Usuario no encontrado.\"}");
        }
        
        User updatedUser = userService.update(id,userDetails);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> optionalUser = userService.getById(id);
        
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(404).body("{\"message\": \"Usuario no encontrado.\"}");
        }
        
        userService.delete(id);
        return ResponseEntity.ok("{\"message\": \"Usuario eliminado con éxito.\"}");
    }
}