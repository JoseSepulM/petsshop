package com.example.petsshop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRODUCT")
    private Long idProduct;

    @Column(name = "NAMEPRODUCT")
    private String nameProduct;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "price")
    private Integer price;


    public Long getIdProduct(){
        return idProduct;
    }

    public String getNameProduct(){
        return nameProduct;
    }

    public String getDescription(){
        return descripcion;
    }

    public Integer getPrice(){
        return price;
    }

    public void setIdProduct(Long idProduct){
        this.idProduct = idProduct;
    }

    public void setNameProduct(String name){
        this.nameProduct = name;
    }

    public void setDescription(String desc){
        this.descripcion = desc;
    }

    public void setPrice(Integer price){
        this.price = price;
    }
}

