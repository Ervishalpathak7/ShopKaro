package com.shopkaro.productService.Services;

import com.shopkaro.productService.Entity.Product;
import com.shopkaro.productService.Repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticSearchService {
    private final ProductRepository productRepository;

    @Autowired
    public ElasticSearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Find by name (case-insensitive)
    public List<Product> findProductsByName(String name) {
        return productRepository.findProductsByName(name);
    }

    // Find by category
    public List<Product> findProductsByCategory(String categoryId) {
        return productRepository.findProductsByCategory(categoryId);
    }

    // Find by price range
    public List<Product> findProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findProductsByPriceRange(minPrice, maxPrice);
    }

    // Find by brand (case-insensitive)
    public List<Product> findProductsByBrand(String brand) {
        return productRepository.findProductsByBrand(brand);
    }

    // Find with multiple filters
    public List<Product> findProductsByFilters(ObjectId categoryId, Double minPrice,
                                               Double maxPrice, String brand,
                                               Boolean isAvailable) {
        return productRepository.findProductsByFilters(categoryId, minPrice, maxPrice, brand, isAvailable);
    }

    // Advanced search with pagination and sorting
    public List<Product> searchProducts(String searchTerm, int page, int size, String sortField) {
        return productRepository.searchProducts(searchTerm, page, size, sortField);
    }
}
