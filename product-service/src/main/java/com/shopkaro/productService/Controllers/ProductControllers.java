package com.shopkaro.productService.Controllers;

import com.shopkaro.productService.Entity.Product;
import com.shopkaro.productService.Services.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductControllers {

    private final ProductService productService;

    @Autowired
    public ProductControllers(ProductService productServices) {
        this.productService = productServices;
    }


    // Add a product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Add multiple products
    @PostMapping("/bulkadd")
    public ResponseEntity<Collection<Product>> createProduct(@RequestBody List<Product> product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get a product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable ObjectId id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a product by id
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable ObjectId id,
                                                 @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // Delete a product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable ObjectId id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    // Search by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Product>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

    // Search by category
    @GetMapping("/search/category/{category}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.findProductsByCategory(category));
    }

    // Search by price range
    @GetMapping("/search/price")
    public ResponseEntity<List<Product>> findByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(productService.findProductsByPriceRange(minPrice, maxPrice));
    }

    // Search by brand
    @GetMapping("/search/brand")
    public ResponseEntity<List<Product>> findByBrand(@RequestParam String brand) {
        return ResponseEntity.ok(productService.findProductsByBrand(brand));
    }

    // Search by multiple filters
    @GetMapping("/search/filter")
    public ResponseEntity<List<Product>> findByFilters(
            @RequestParam(required = false) ObjectId categoryId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Boolean isAvailable) {
        return ResponseEntity.ok(productService.findProductsByFilters(
                categoryId, minPrice, maxPrice, brand, isAvailable));
    }

    // Search by search term
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortField) {
        return ResponseEntity.ok(productService.searchProducts(searchTerm, page, size, sortField));
    }
}

