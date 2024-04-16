package com.example.petsshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.petsshop.repository.ProductRepository;
import com.example.petsshop.model.Product;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long idProduct){
        return productRepository.findById(idProduct);
    }

    @Override
    public Product updateProduct(Product payload, Long idProduct){
        if(productRepository.existsById(idProduct)){
            payload.setIdProduct(idProduct);
            return productRepository.save(payload);
        }

        return null;
    }
    
    @Override
    public Product createProduct(Product payload){
        return productRepository.save(payload);
    }

    @Override 
    public void deleteProduct(Long idProduct){
        productRepository.deleteById(idProduct);
    }
}