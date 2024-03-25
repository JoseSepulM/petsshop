package com.example.petsshop;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class SaleController {
    
    private SaleService saleService;
    private ProductService productService;

    public SaleController( SaleService saleService, ProductService productService){
        this.saleService = saleService;
        this.productService = productService;
    }

    @GetMapping("sales")
    public List<Sale> getSales() {
        return saleService.getAllSale();
    }

    @GetMapping("sales/{idSale}")
    public Sale getSalesById(@PathVariable int idSale) {
        return saleService.getSaleById(idSale);
    }

    @GetMapping("sales/{idSale}/detail")
    public Map<String, Object> getSalesByIdDetail(@PathVariable int idSale) {
        Sale sale = saleService.getSaleById(idSale);
        if(sale == null){
            return null;
        }
        int[] idproducts = sale.getProductsSelected();
        List<Product> productsSelected = new ArrayList<>();
        int total = 0;
        for(int idproduct : idproducts){
            Product product = productService.getProductById(idproduct);
            if(product != null){
                total = total + product.getPrice();
                productsSelected.add(product);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("idSale", sale.getIdSale());
        result.put("dateSale", sale.getDateSale());
        result.put("productsSelected", productsSelected);
        result.put("totalSale", total);
        return result;

    }

 
    

    @GetMapping("sales/year/{year}")
    public Map<String, Object> getSalesByYear(@PathVariable int year) {
        List<Sale> salesbyyear = saleService.getSaleByYear(year);
        int total = 0;
        for(Sale sale : salesbyyear){
            int[] idproducts = sale.getProductsSelected();
            for(int idproduct : idproducts){
                Product product = productService.getProductById(idproduct);
                if(product != null){
                    total = total + product.getPrice();
                }
            }
        }
        
        Map<String, Object> result = new HashMap<>();
      
        result.put("totalSales", salesbyyear.size());
        result.put("totalSale", total);
        return result;
    }

    @GetMapping("sales/month/{year}/{month}")
    public Map<String, Object> getSalesByMonth(@PathVariable int year, @PathVariable int month) {
        List<Sale> salesbymonth = saleService.getSaleByMonth(year, month);
        int total = 0;
        for(Sale sale : salesbymonth){
            int[] idproducts = sale.getProductsSelected();
            for(int idproduct : idproducts){
                Product product = productService.getProductById(idproduct);
                if(product != null){
                    total = total + product.getPrice();
                }
            }
        }
        
        Map<String, Object> result = new HashMap<>();
      
        result.put("totalSales", salesbymonth.size());
        result.put("totalSale", total);
        return result;
    }

    @GetMapping("sales/salesbydate/{day}")
    public Map<String, Object> getSalesByDate(@PathVariable LocalDate day) {
        List<Sale> salesByDay = saleService.getSalebyDay(day);
        int total = 0;
        for(Sale sale : salesByDay){
            int[] idproducts = sale.getProductsSelected();
            for(int idproduct : idproducts){
                Product product = productService.getProductById(idproduct);
                if(product != null){
                    total = total + product.getPrice();
                }
            }
        }
        
        Map<String, Object> result = new HashMap<>();
      
        result.put("totalSales", salesByDay.size());
        result.put("totalSale", total);
        return result;
    }
    
}
