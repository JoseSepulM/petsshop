package com.example.petsshop;
import java.time.LocalDate;


public class Sale {
    private int idSale;
    private LocalDate dateSale;
    private int[] productsSelected;

    public Sale(int idSale, LocalDate dateSale, int[] productsSelected){
        this.idSale = idSale;
        this.dateSale = dateSale;
        this.productsSelected = productsSelected;
    }

    // GET

    public int getIdSale(){
        return idSale;
    }

    public LocalDate getDateSale(){
        return dateSale;
    }

    public int[] getProductsSelected(){
        return productsSelected;
    }


}
