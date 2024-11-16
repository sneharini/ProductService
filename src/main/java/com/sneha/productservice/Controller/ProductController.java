package com.sneha.productservice.Controller;

import com.sneha.productservice.models.Product;
import com.sneha.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    // injecting object using DI
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    //http://localhost:8080/products/10
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable ("id") long id){
        return productService.getSingleProduct(id);
    }
    public List<Product> getAllProducts(){
        return new ArrayList<>();
    }

//    public addProduct(){
//
//    }
}
