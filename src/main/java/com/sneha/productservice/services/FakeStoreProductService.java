package com.sneha.productservice.services;

import com.sneha.productservice.dtos.FakeStoreProductDto;
import com.sneha.productservice.exception.ProductNotFoundException;
import com.sneha.productservice.models.Category;
import com.sneha.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreproductservice")
//@Primary
public class FakeStoreProductService implements ProductService{
    // injecting restTemplate into fakestoreProductService
    private RestTemplate restTemplate;
    //injecting redisTemplate into fakestoreProductService
    private RedisTemplate<String, Object> redisTemplate;


    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        // using redis
        // try to fetch the product from redis
        // redis return an object, so need to typecase it with product
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT" +productId);

        if(product != null){
            // cache hit
            return product;
        }
        //cache miss


        // call FakeStore to fetch the Product with the give id via HTTP call
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );

        if(fakeStoreProductDto == null) {
            throw new ProductNotFoundException("product with id" + productId + "doesn't exist", productId);
        }


        // before returning, store it in cache

        product = convertFakeStoreProductToProduct(fakeStoreProductDto);
        // store it in redis
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCT_" +productId,product);
        return product;
    }


    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        //convert list of FakeStoreProductDto into list of product
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public List<Product> getCategoryProduct(String categoryName) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + categoryName,
                FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }
        return products;
    }

    //partial update
    @Override
    public Product updateProduct(Long productId, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductService.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + productId, HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);

    }
    // complete update
    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }


    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        // convert fakestoreproductdto into product

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(category);

        return product;
    }
}
