package com.example.petsshop.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

import com.example.petsshop.model.Product;
import com.example.petsshop.service.ProductService;


import static org.hamcrest.Matchers.equalTo;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{

        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setDescription("Comida para gato cachorro");
        product1.setNameProduct("Master Cat Kitten");
        product1.setPrice(7990);

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setDescription("Comida para gato adulto");
        product2.setNameProduct("Master Cat senior");
        product2.setPrice(8560);

        List<Product> products = List.of(product1, product2);


        List<EntityModel<Product>> productRepository = products.stream()
        .map(product -> EntityModel.of(product))
        .collect(Collectors.toList());

        when(productServiceMock.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
        .andExpect(status().isOk())

        .andExpect(jsonPath("$._embedded.productList.length()").value(equalTo(2)))
        .andExpect(jsonPath("$._embedded.productList[0].price").value(equalTo(7990)))
        .andExpect(jsonPath("$._embedded.productList[1].nameProduct").value("Master Cat senior"))
        .andExpect(jsonPath("$._embedded.productList[0]._links.self.href").value("http://localhost/products/1"))
        .andExpect(jsonPath("$._embedded.productList[1]._links.self.href").value("http://localhost/products/2"));

    }

    @Test
    public void obtenerPostTest() throws Exception {
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setDescription("Comida para gato cachorro");
        product1.setNameProduct("Master Cat Kitten");
        product1.setPrice(7990);

        when(productServiceMock.getProductById(1L)).thenReturn(Optional.of(product1));

        mockMvc.perform(get("/products/{idProduct}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(equalTo(7990)))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/products/1"));
    }


}
