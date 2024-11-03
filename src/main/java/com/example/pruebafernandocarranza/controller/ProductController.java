/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.pruebafernandocarranza.controller;

import com.example.pruebafernandocarranza.model.Category;
import com.example.pruebafernandocarranza.model.Product;
import com.example.pruebafernandocarranza.service.CategoryService;
import com.example.pruebafernandocarranza.service.ProductService;
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
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Product> index() {
        return productService.all();
    }

    @PostMapping
    public Product store(@RequestBody ProductRequest productRequest) {
        // Busca la categoría usando el ID proporcionado en ProductRequest
        Optional<Category> categoryOptional = categoryService.getById(productRequest.getCategory());

        // Verifica si la categoría existe
        if (categoryOptional.isPresent()) {
            // Crea un nuevo producto y asigna los datos del request
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setCategory(categoryOptional.get());

            // Guarda y retorna el producto
            return productService.save(product);
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    }

    @GetMapping("/{id}")
    public Optional<Product> show(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
public Product update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
    // Busca el producto por su ID
    Optional<Product> existingProductOptional = productService.getById(id);
    
    if (existingProductOptional.isPresent()) {
        Product existingProduct = existingProductOptional.get();
        
        // Busca la categoría por el ID proporcionado en el ProductRequest
        Optional<Category> categoryOptional = categoryService.getById(productRequest.getCategory());
        
        if (categoryOptional.isPresent()) {
            // Actualiza los datos del producto con los datos de ProductRequest
            existingProduct.setName(productRequest.getName());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setCategory(categoryOptional.get());
            
            // Guarda y retorna el producto actualizado
            return productService.save(existingProduct);
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    } else {
        throw new RuntimeException("Producto no encontrado");
    }
}


    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        productService.delete(id);
    }

    public static class ProductRequest {

        private Long id;
        private String name;
        private String description;
        private double price;
        private Long category;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public Long getCategory() {
            return category;
        }

        public void setCategory(Long category) {
            this.category = category;
        }

    }
}
