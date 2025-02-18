package com.shopkaro.productService.Repositories;

import com.shopkaro.productService.Entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductRepository {

    private final MongoTemplate mongoTemplate;

    public ProductRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Add a product
    public Product createProduct(Product product) {
        return mongoTemplate.save(product);
    }

    // Add multiple products
    public Collection<Product> createProduct(List<Product> products) {
        return mongoTemplate.insertAll(products);
    }

    // Get a product by id
    public Product getProductById(ObjectId id) {
        return mongoTemplate.findById(id, Product.class);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return mongoTemplate.findAll(Product.class);
    }

    // Delete a product
    public void deleteProduct(ObjectId id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Product.class);
    }

    // Optimized Update using $set (avoiding unnecessary fetches)
    public Product updateProduct(ObjectId id, Product productDetails) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("name", productDetails.getName())
                .set("description", productDetails.getDescription())
                .set("price", productDetails.getPrice())
                .set("quantity", productDetails.getQuantity())
                .set("category", productDetails.getCategory())
                .set("brand", productDetails.getBrand())
                .set("available", productDetails.isAvailable());

        mongoTemplate.updateFirst(query, update, Product.class);
        return mongoTemplate.findOne(query, Product.class);
    }

    // Find products by name (case-insensitive, optimized)
    public List<Product> findProductsByName(String name) {
        return mongoTemplate.find(
                new Query(Criteria.where("name").regex("^" + name, "i")),
                Product.class
        );
    }

    // Find products by category
    public List<Product> findProductsByCategory(String category) {
        return mongoTemplate.find(
                new Query(Criteria.where("category").regex(category, "i")),
                Product.class
        );
    }

    // Find by price range
    public List<Product> findProductsByPriceRange(Double minPrice, Double maxPrice) {
        Query query = new Query();
        if (minPrice != null) query.addCriteria(Criteria.where("price").gte(minPrice));
        if (maxPrice != null) query.addCriteria(Criteria.where("price").lte(maxPrice));
        return mongoTemplate.find(query, Product.class);
    }

    // Find by brand (case-insensitive)
    public List<Product> findProductsByBrand(String brand) {
        return mongoTemplate.find(
                new Query(Criteria.where("brand").regex(brand, "i")),
                Product.class
        );
    }

    // findProductsByFilters with multiple filters
    public List<Product> findProductsByFilters(ObjectId categoryId, Double minPrice,
                                               Double maxPrice, String brand,
                                               Boolean isAvailable) {
        Query query = new Query();

        if (categoryId != null) query.addCriteria(Criteria.where("category.id").is(categoryId));
        if (minPrice != null) query.addCriteria(Criteria.where("price").gte(minPrice));
        if (maxPrice != null) query.addCriteria(Criteria.where("price").lte(maxPrice));
        if (brand != null) query.addCriteria(Criteria.where("brand").regex(brand, "i"));
        if (isAvailable != null) query.addCriteria(Criteria.where("available").is(isAvailable));

        return mongoTemplate.find(query, Product.class);
    }

    // Optimized search with pagination & sorting
    public List<Product> searchProducts(String searchTerm, int page, int size, String sortField) {
        Query query = new Query();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            Criteria criteria = new Criteria().orOperator(
                    Criteria.where("name").regex(searchTerm, "i"),
                    Criteria.where("description").regex(searchTerm, "i"),
                    Criteria.where("brand").regex(searchTerm, "i")
            );
            query.addCriteria(criteria);
        }

        // Pagination
        query.skip((long) page * size).limit(size);

        // Sorting
        if (sortField != null && !sortField.isEmpty()) {
            query.with(org.springframework.data.domain.Sort.by(sortField));
        }

        return mongoTemplate.find(query, Product.class);
    }
}
