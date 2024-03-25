package com.example.petsshop;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.stereotype.Service;


@Service
public class SaleService {
    private List<Sale> sales = new ArrayList<>();
    
    public SaleService(List<Sale> sales){
        this.sales = sales;
        int[] arrayIdProducts = {1,3,2};
        sales.add(new Sale(1,LocalDate.of(2024,01,03), arrayIdProducts ));

        int[] arrayIdProducts2 = {1,5};
        sales.add(new Sale(2,LocalDate.of(2024,01,23), arrayIdProducts2 ));

        int[] arrayIdProducts3 = {2,4};
        sales.add(new Sale(3,LocalDate.of(2023,06,28), arrayIdProducts3 ));

        int[] arrayIdProducts4 = {5};
        sales.add(new Sale(4,LocalDate.of(2023,06,11), arrayIdProducts4 ));
    }
    

    public List<Sale> getAllSale(){
        return sales;
    }

    public Sale getSaleById(int idSale){
        for(Sale sale : sales){
            if(sale.getIdSale() == idSale){
                return sale;
            }
        }
        return null;
    }

    public int[] getDetailProductsByIdSale(int idSale){

        Sale sale = this.getSaleById(idSale);
        if(sale != null){
            return sale.getProductsSelected();
        }
        return null;
    }

    public List<Sale> getSaleByYear(int year){
        List<Sale> salesYear = new ArrayList<>();
        
        for(Sale sale : sales){
            LocalDate dateSale = sale.getDateSale();
            if(dateSale.getYear() == year){
                salesYear.add(sale);
            }
        }

        return salesYear;
    }

    public List<Sale> getSaleByMonth(int year, int month){
        List<Sale> salesMonth = new ArrayList<>();
        
        for(Sale sale : sales){
            LocalDate dateSale = sale.getDateSale();
            if(dateSale.getYear() == year && dateSale.getMonthValue() == month){
                salesMonth.add(sale);
            }
        }
        
        return salesMonth;
    }

    public List<Sale> getSalebyDay(LocalDate day){
        List<Sale> salesDay = new ArrayList<>();
        for(Sale sale : sales){
            LocalDate dateSale = sale.getDateSale();
            if(dateSale.isEqual(day)){
                salesDay.add(sale);
            }
        }

        return salesDay;
    }


}
