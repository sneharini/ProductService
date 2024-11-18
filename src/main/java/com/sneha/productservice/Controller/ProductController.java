package com.sneha.productservice.Controller;

import com.sneha.productservice.models.Product;
import com.sneha.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> getProductById(@PathVariable ("id") long id){

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

    public void deleteProduct(Long productId){

    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return null;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable Long id, @RequestBody Product product){
        return null;
    }

//    public addProduct(){
//
//    }
}
