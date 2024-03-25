package com.example.petsshop;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private List<Product> products;

    public ProductService(List<Product> products) {
        this.products = products;
        products.add(new Product(1, "Catshow kitten", "Comida para gato cachorro", 14990));
        products.add(new Product(2, "Catshow Senior", "Comida para gato adulto", 18990));
        products.add(new Product(3, "Dogshow cachorro", "Comida para perro cachorro", 12250));
        products.add(new Product(4, "Dogshow senior", "Comida para perro adulto", 16790));
        products.add(new Product(5, "Peineta", "Peineta para gatos", 3990));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(int idProduct) {
        for(Product product : products) {
            if(product.getIdProduct() == idProduct) {
                return product;
            }
        }
        return null; // Si no se encuentra el producto
    }
}
