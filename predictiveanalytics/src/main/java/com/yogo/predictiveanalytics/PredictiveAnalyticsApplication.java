package com.yogo.predictiveanalytics;


import com.yogo.predictiveanalytics.domain.Sale;
import com.yogo.predictiveanalytics.models.SalesForecast;
import com.yogo.predictiveanalytics.repo.SalesRepo;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class PredictiveAnalyticsApplication implements ApplicationRunner
{
    @Autowired
    private SparkSession session;

    @Autowired private RestTemplate template;
    @Autowired
    private SalesRepo salesRepo;
    @Autowired SalesForecast salesForecast;


    public static void main(String[] args) {
        SpringApplication.run(PredictiveAnalyticsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


       salesRepo.count().subscribe(System.out::println);
     List<Sale>sales=  salesRepo
               .findAll()
               .toStream()
               .collect(Collectors.toList());
     salesForecast.setSales(sales);



    }

    @Bean
    public RestTemplate getTemplate(){
        return new RestTemplate();
    }
    @Bean
    public SalesForecast getSalesForecast(){

        return new SalesForecast(session);


    }


}
