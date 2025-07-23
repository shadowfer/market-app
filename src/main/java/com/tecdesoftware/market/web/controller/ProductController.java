package com.tecdesoftware.market.web.controller;

import com.tecdesoftware.market.domain.Product;
import com.tecdesoftware.market.domain.service.ProductService;
import com.tecdesoftware.market.persistence.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
//Le dice a Spring que va a ser el controlador de una API REST
@RestController
@RequestMapping("/products")
@Tag(name="Product Controller", description = "Manage products in the store")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Cambia de /all a simplemente / para obtener todos (más RESTful)
    @GetMapping()
    @Operation(
            summary = "Get all products",
            description = "Return a list of all available products"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of products")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public List<Product> getAll() {
        return productService.getAll();
    }

    // Obtener producto por id - ruta /products/{id}
    @GetMapping("/{id}")
    @Operation(
            summary = "Get products by ID",
            description = "Return a product by its ID if exists"
    )
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Optional<Product> getProduct(
            @Parameter(description = "Id of the product to be retrieved",
            example = "7",  required = true)
            @PathVariable("id") int productId) {
        return productService.getProduct(productId);
    }

    // Obtener productos por categoría - usa una ruta distinta para evitar conflicto
    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get products by Category",
            description = "Return all products in a specific category"
    )
    @ApiResponse(responseCode = "200", description = "Products found in the category")
    @ApiResponse(responseCode = "204", description = "No product found in the category")
    @ApiResponse(responseCode = "404", description = "Category not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Optional<List<Product>> getByCategory(
            @Parameter(description = "Category ID",
                    example = "7",  required = true)
            @PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    // Crear producto - indica que el product viene en el body
    @PostMapping
    @Operation(
            summary = "Save a new product",
            description = "Registers a new product and returns the created product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example Product",
                                    value = """
                            {
                                "name" : "Ian Coke",
                                "categoryId" : 2,
                                "price" : "99.50",
                                "stock" : 30,
                                "active" : true
                            }
                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product conflict (duplicate code or SKU)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    // Eliminar producto por id - usa @PathVariable también
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by ID", description = "Deletes a product if it exists")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public boolean delete(
            @Parameter(
                    description = "ID of the product to be deleted",
                    example = "7",
                    required = true)
            @PathVariable("id") int productId) {
        return productService.delete(productId);
    }
}
