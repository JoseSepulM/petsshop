package com.example.petsshop;


import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public List<Product> getMethodName() {
        return productService.getAllProducts();
    }
    
    @GetMapping("products/{idProduct}")
    public Product getMethodName(@PathVariable int idProduct) {
        return productService.getProductById(idProduct);
    }
    
}
