package com.shopkaro.productService.Services;

import com.shopkaro.productService.Entity.Product;
import com.shopkaro.productService.Repositories.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create
    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    // Create multiple
    public Collection<Product> createProduct(List<Product> products) {
        return productRepository.createProduct(products);
    }

    // Read all
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    // Read by id
    public Optional<Product> getProductById(ObjectId id) {
        return Optional.ofNullable(productRepository.getProductById(id));
    }

    // Update
    public Product updateProduct(ObjectId id, Product productDetails) {
        return productRepository.updateProduct(id, productDetails);
    }

    // Delete
    public void deleteProduct(ObjectId id) {
        productRepository.deleteProduct(id);
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