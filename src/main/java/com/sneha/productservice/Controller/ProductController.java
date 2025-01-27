package com.sneha.productservice.Controller;

import com.sneha.productservice.exception.ProductNotFoundException;
import com.sneha.productservice.models.Product;
import com.sneha.productservice.repositories.ProductRepository;
import com.sneha.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private ProductService productService;

    // injecting object using DI
    public ProductController(@Qualifier("selftproductservice") ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }



    //http://localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable ("id") long id) throws ProductNotFoundException {

//        ResponseEntity<Product> responseEntity = new ResponseEntity<>(
//                productService.getSingleProduct(id),
//                HttpStatus.OK
//        );

        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
        return response;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getCategory(@PathVariable("categoryName") String categoryName){
        return productService.getCategoryProduct(categoryName);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);

    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(id, product);
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
}
