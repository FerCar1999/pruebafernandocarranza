/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pruebafernandocarranza.controller;

import com.example.pruebafernandocarranza.model.Category;
import com.example.pruebafernandocarranza.service.CategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fer
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public List<Category> index(){
        return categoryService.all();
    }
    
    @PostMapping
    public Category store(@RequestBody Category category){
        return categoryService.save(category);
    }
    
    @GetMapping("/{id}")
    public Optional<Category> show(@PathVariable Long id){
        return categoryService.getById(id);
    }
    
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category){
        return categoryService.update(id, category);
    }
    
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id){
        categoryService.delete(id);
    }
    
}
