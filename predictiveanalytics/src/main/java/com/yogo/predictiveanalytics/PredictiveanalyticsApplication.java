package com.yogo.predictiveanalytics;

import com.yogo.predictiveanalytics.models.SalesForecast;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PredictiveanalyticsApplication implements ApplicationRunner
{
    @Autowired
    private SparkSession session;

    @Autowired private RestTemplate template;


    public static void main(String[] args) {
        SpringApplication.run(PredictiveanalyticsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


    }

    @Bean
    public RestTemplate getTemplate(){
        return new RestTemplate();
    }
    @Bean
    public SalesForecast getSales(){
        return new SalesForecast(session);
    }


}
