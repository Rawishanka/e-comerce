package com.pm.productservice.mapper;

import com.pm.productservice.dto.ProductResponseDto;
import com.pm.productservice.model.Products;

public class ProductMapper {
    static public ProductResponseDto toProductResponseDto(Products products) {
        ProductResponseDto productResponseDto = new ProductResponseDto(products.getId(), products.getName(), products.getDescription(), products.getPrice());
        return productResponseDto;
    }
}
