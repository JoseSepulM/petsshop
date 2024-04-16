package com.example.petsshop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.petsshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}