package com.sneha.productservice.services;

import com.sneha.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);
    List<Product> getAllProducts();
    List<Product> getCategoryProduct(String categoryName);
    Product updateProduct(Long productId, Product product);
    Product replaceProduct(Long productId, Product product);



}
