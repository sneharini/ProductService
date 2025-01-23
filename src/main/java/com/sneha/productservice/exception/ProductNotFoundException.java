package com.sneha.productservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class ProductNotFoundException extends Exception{
    private Long productId;
    public ProductNotFoundException(String message, Long productId){
        super(message);
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
