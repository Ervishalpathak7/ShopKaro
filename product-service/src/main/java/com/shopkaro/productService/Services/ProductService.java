package com.shopkaro.productService.Services;

import com.shopkaro.productService.Entity.Product;
import com.shopkaro.productService.Repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RedisService redisService;

    @Autowired
    public ProductService(ProductRepository productRepository, RedisService redisService) {
        this.productRepository = productRepository;
        this.redisService = redisService;
    }

    // Create
    public Product createProduct(Product product) {
        // Save the product in MongoDB
        Product savedProduct = productRepository.createProduct(product);
        // Save the product in Redis
        redisService.save(savedProduct.getId().toString(), savedProduct);
        // Return the saved product
        return savedProduct;
    }

    // Read by id
    public Optional<Product> getProductById(ObjectId id) {
        // Check if the product is present in Redis
        Product product = redisService.find(id.toString());
        if (product != null) {
            return Optional.of(product);
        }
        Optional<Product> productById = Optional.ofNullable(productRepository.getProductById(id));
        // Save the product in Redis
        productById.ifPresent(value -> redisService.save(value.getId().toString(), value));
        return productById;
    }

    // Update
    public Product updateProduct(ObjectId id, Product product) {
        Product updatedProduct = productRepository.updateProduct(id, product);
        // Update the product in Redis
        redisService.update(updatedProduct.getId().toString(), updatedProduct);
        return updatedProduct;
    }

    // Delete
    public void deleteProduct(ObjectId id) {
        productRepository.deleteProduct(id);
        // Delete the product from Redis
        redisService.delete(id.toString());
    }
}