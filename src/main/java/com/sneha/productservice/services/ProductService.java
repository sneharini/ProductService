package com.sneha.productservice.services;

import com.sneha.productservice.exception.ProductNotFoundException;
import com.sneha.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    List<Product> getCategoryProduct(String categoryName);
    Product updateProduct(Long productId, Product product) throws ProductNotFoundException;
    Product replaceProduct(Long productId, Product product) throws ProductNotFoundException;
    Product addProduct(Product product);
    void deleteProduct(Long productId);


}
