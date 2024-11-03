/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pruebafernandocarranza.service;

import com.example.pruebafernandocarranza.model.Product;
import com.example.pruebafernandocarranza.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fer
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    public Product save(Product product){
        return productRepository.save(product);
    }
    
    public Product update(Long id,Product product_updated){
        Optional<Product> product = this.getById(id);
        if (product.isPresent()) {
            product_updated.setId(id);
            return productRepository.save(product_updated);
        }
        return null; 
    }
    
    public void delete(Long id){
        Optional<Product> category = this.getById(id);
        category.ifPresent(c->{
            c.softDelete();
            productRepository.save(c);
        });
    }
    
    public List<Product> all(){
        return productRepository.findAll();
    }
    
    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }
}
