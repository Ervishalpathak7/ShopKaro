package com.shopkaro.productService.Entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private ObjectId id;

    @NonNull
    @Indexed
    private String brand;

    @Indexed
    private boolean available;

    @NonNull
    @Indexed
    private String name;

    @NonNull
    private String description;

    @NonNull
    @Indexed
    private String category;

    @NonNull
    @Indexed
    private Double price;

    @NonNull
    private Integer quantity;

    @NonNull
    private List<String> images;
}
