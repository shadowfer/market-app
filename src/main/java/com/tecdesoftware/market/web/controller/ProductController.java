package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.service.ProductService;
import com.tecdesoftware.market.persistence.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Le dice a Spring que va a ser el controlador de una API REST
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Cambia de /all a simplemente / para obtener todos (más RESTful)
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    // Obtener producto por id - ruta /products/{id}
    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    // Obtener productos por categoría - usa una ruta distinta para evitar conflicto
    @GetMapping("/category/{categoryId}")
    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    // Crear producto - indica que el product viene en el body
    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    // Eliminar producto por id - usa @PathVariable también
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }
}
