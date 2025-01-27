package com.sneha.productservice.repositories;

import com.sneha.productservice.ProductWithIdAndTitle;
import com.sneha.productservice.Projections;
import com.sneha.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    //Product repo should contain all the methods (CRUD) related to product model

    Optional<Product> findById(Double id);
    // select * from products where id = 1;
    List<Product> findAll();

    List<Product> findProductByTitle(String title);

    List<Product> findTop5ByTitleContains(String title);
    //select * from products where title like '' LIMIT 5;

    List<Product> findProductsByTitleContainsAndPriceGreaterThan(String title, Double price);

    //HQL query
    @Query("select p.id as id, p.title as title from Product p") // using model name directly
    List<ProductWithIdAndTitle> randomSearchProducts();

    // Native Query -- SQL
    @Query(value = "select * from product p where p.id = :productId", nativeQuery = true )
    Product randomSearchProducts2(Long productId);
}


/*
1. Repository should be an interface
2. Repository should extend JPARepository<model name, primary key datatype>
 */