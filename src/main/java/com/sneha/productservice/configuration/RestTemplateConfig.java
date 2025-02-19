package com.sneha.productservice.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean // create a single object of the class in IOC container
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public RedisTemplate<String , Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String , Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
