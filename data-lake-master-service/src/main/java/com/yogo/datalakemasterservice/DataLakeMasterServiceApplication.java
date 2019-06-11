package com.yogo.datalakemasterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DataLakeMasterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataLakeMasterServiceApplication.class, args);
    }

}
