package com.example.petsshop.controller;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    @GetMapping()
    public CollectionModel<EntityModel<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        log.info("GET /products");
        log.info("Retornando todos los productos");

        List<EntityModel<Product>> productResources = products.stream()
        .map( product -> EntityModel.of(product,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductById(product.getIdProduct())).withSelfRel()
        ))
        .collect(Collectors.toList());
        
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts());
        CollectionModel<EntityModel<Product>> resources = CollectionModel.of(productResources, linkTo.withRel("products"));

        return resources;
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> getProductById(@PathVariable Long idProduct) {
        Optional<Product> optionalProduct = productService.getProductById(idProduct);

        log.info("GET /product");
        log.info("Retornando un solo producto");

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Product not found with id: " + idProduct));
        }
        
        Product product = optionalProduct.get();
        EntityModel<Product> productResource = EntityModel.of(product,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getProductById(idProduct)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts()).withRel("allProducts"));
    
        return ResponseEntity.ok(productResource);
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
