package com.shopkaro.productService.Services;


import com.shopkaro.productService.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Save the product in Redis
    public void save(String key, Product product) {
        System.out.println("Saving to Redis");
        redisTemplate.opsForValue().set(key, product);
    }

    // update product in Redis
    public void update(String key, Product product) {
        System.out.println("Updating Redis");
        redisTemplate.opsForValue().set(key, product);
    }

    // Find the product in Redis
    public Product find(String key) {
        System.out.println("Fetching from Redis");
        return (Product) redisTemplate.opsForValue().get(key);
    }

    // Delete the product from Redis
    public void delete(String key) {
        System.out.println("Deleting from Redis");
        redisTemplate.delete(key);
    }
}
