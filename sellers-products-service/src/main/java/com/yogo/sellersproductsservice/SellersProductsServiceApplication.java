package com.yogo.sellersproductsservice;

import com.yogo.sellersproductsservice.repo.SellersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SellersProductsServiceApplication  {
    @Autowired
    private SellersRepo sellersRepo;

    public static void main(String[] args) {
        SpringApplication.run(SellersProductsServiceApplication.class, args);

    }


}
