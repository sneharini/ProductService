package com.sneha.productservice.services;

import com.sneha.productservice.exception.ProductNotFoundException;
import com.sneha.productservice.models.Category;
import com.sneha.productservice.models.Product;
import com.sneha.productservice.repositories.CategoryRepository;
import com.sneha.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("selftproductservice")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    //dependency injection
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        // make a call to database to fetch product with given id.
        // we should not call the db directly from service layer, instead use repository class to fetch

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist", productId);
        }
        return productOptional.get();
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getCategoryProduct(String categoryName) {
        return List.of();
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {

        // update few parameters in the given productId
        // we need to get the product first
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist", productId);
        }

        Product productInDb = productOptional.get();

        if(product.getTitle() != null){
            productInDb.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productInDb.setPrice(product.getPrice());
        }
        return productRepository.save(productInDb);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist", productId);
        }
        Product productInDb = productOptional.get();

        if(product.getTitle() != null){
            productInDb.setTitle(product.getTitle());
        }
        if(product.getPrice() != null){
            productInDb.setPrice(product.getPrice());
        }

        if(product.getCategory() != null){
            productInDb.setCategory(product.getCategory());
        }
        return productRepository.save(productInDb);
    }

    @Override
    public Product addProduct(Product product) {
        Category category =  product.getCategory();

        // if the category is not mentioned while adding the newProduct
        if(category.getId() == null){
            // we need to create a new category object in the database first
            category = categoryRepository.save(category);
            product.setCategory(category);
        }
        return productRepository.save(product);
    }


}
