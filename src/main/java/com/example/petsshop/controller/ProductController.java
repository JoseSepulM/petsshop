package com.example.petsshop.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.petsshop.model.Product;
import com.example.petsshop.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getProductById(@PathVariable Long idProduct){
        Optional <Product> product = productService.getProductById(idProduct);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Product not found with id: " + idProduct));
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping()
    public Product addPost(@RequestBody Product payload){
        return productService.createProduct(payload);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<?> updateProduct(@RequestBody Product payload, @PathVariable Long idProduct){
        Optional <Product> product = productService.getProductById(idProduct);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Product not found with id: " + idProduct));
        }

        productService.updateProduct(payload, idProduct);
        return ResponseEntity.ok("Update product!");
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> deletePost(@PathVariable Long idProduct){
        
        Optional <Product> product = productService.getProductById(idProduct);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Product not found with id: " + idProduct));
        }
        
        productService.deleteProduct(idProduct);
        return ResponseEntity.ok("Delete product!");
    }


    static class ErrorResponse{
        private final String message;
        
        public ErrorResponse(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }



}
