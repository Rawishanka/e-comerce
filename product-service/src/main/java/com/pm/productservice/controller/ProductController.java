package com.pm.productservice.controller;

import com.pm.productservice.dto.ProductRequestDto;
import com.pm.productservice.dto.ProductResponseDto;
import com.pm.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Products", description = "API End points for products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a product")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        var result = this.productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "get all products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        var results = this.productService.getAllProducts();
        return ResponseEntity.ok(results);
    }

}
