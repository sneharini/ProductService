package com.sneha.productservice.services;

import com.sneha.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
}
