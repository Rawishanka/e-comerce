package com.pm.productservice.repository;

import com.pm.productservice.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products, String> {
}
