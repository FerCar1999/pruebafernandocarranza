/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pruebafernandocarranza.service;

import com.example.pruebafernandocarranza.model.User;
import com.example.pruebafernandocarranza.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author fer
 */
@Service
public class UserService {
        @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User update(Long id,User user_updated){
        Optional<User> user = this.getById(id);
        if (user.isPresent()) {
            user_updated.setId(id);
            user_updated.setPassword(passwordEncoder.encode(user_updated.getPassword()));
            return userRepository.save(user_updated);
        }
        return null; 
    }
    
    public void delete(Long id){
        Optional<User> user = this.getById(id);
        user.ifPresent(u->{
            u.softDelete();
            userRepository.save(u);
        });
    }
    
    public List<User> all(){
        return userRepository.findAll();
    }
    
    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }
    
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
