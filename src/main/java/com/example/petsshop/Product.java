package com.example.petsshop;

public class Product {

    private int idProduct;
    private String nameProduct;
    private String descripcion;
    private int price;

    public Product(int idProduct, String nameProduct, String description, int price){
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.descripcion = description;
        this.price = price;
    }
    

    // GET
    public int getIdProduct(){
        return idProduct;
    }

    public String getNameProduct(){
        return nameProduct;
    }

    public String getDescription(){
        return descripcion;
    }

    public int getPrice(){
        return price;
    }
}
