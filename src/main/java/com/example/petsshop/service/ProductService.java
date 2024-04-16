
package com.example.petsshop.service;

import com.example.petsshop.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product createProduct(Product payload);
    Product updateProduct(Product payload, Long id);
    void deleteProduct(Long id);
} 