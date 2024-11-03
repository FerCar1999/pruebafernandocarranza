/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pruebafernandocarranza.service;

import com.example.pruebafernandocarranza.model.Category;
import com.example.pruebafernandocarranza.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fer
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category save(Category category){
        return categoryRepository.save(category);
    }
    
    public Category update(Long id,Category category_updated){
        Optional<Category> category = this.getById(id);
        if (category.isPresent()) {
            category_updated.setId(id);
            return categoryRepository.save(category_updated);
        }
        return null; 
    }
    
    public void delete(Long id){
        Optional<Category> category = this.getById(id);
        category.ifPresent(c->{
            c.softDelete();
            categoryRepository.save(c);
        });
    }
    
    public List<Category> all(){
        return categoryRepository.findAll();
    }
    
    public Optional<Category> getById(Long id){
        return categoryRepository.findById(id);
    }
}
