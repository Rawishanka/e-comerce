package com.pm.productservice.service;

import com.pm.productservice.dto.ProductRequestDto;
import com.pm.productservice.dto.ProductResponseDto;
import com.pm.productservice.mapper.ProductMapper;
import com.pm.productservice.model.Products;
import com.pm.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        if (productRequestDto == null) {
            throw new IllegalArgumentException("Product request cannot be null");
        }
        Products products = Products.builder().name(productRequestDto.name()).description(productRequestDto.description()).price(productRequestDto.price()).build();
        productRepository.save(products);
        return new ProductResponseDto(products.getId(), products.getName(), products.getDescription(), products.getPrice());
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Products> products = this.productRepository.findAll();
        List<ProductResponseDto> productResponseDtos = products.stream().map(ProductMapper::toProductResponseDto).toList();
        return productResponseDtos;
    }
}
