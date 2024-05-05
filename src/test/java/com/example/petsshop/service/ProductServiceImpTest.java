package com.example.petsshop.service;

import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.petsshop.model.Product;
import com.example.petsshop.repository.ProductRepository;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(ProductServiceImp.class)
public class ProductServiceImpTest {

    @MockBean
    private ProductRepository productRepository;


    @Test
    public void getAllProductsTest() {
        
        List<Product> products = List.of(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        ProductService productService = new ProductServiceImp(productRepository);
        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
    }

    @Test
    public void getProductByIdTest() {
        // Configuración del mock
        Product product = new Product();
        product.setIdProduct(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Llamada al servicio
        ProductService productService = new ProductServiceImp(productRepository);
        Optional<Product> result = productService.getProductById(1L);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdProduct());
    }
    
}
