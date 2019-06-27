package com.yogo.descriptiveanalyticsservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class DescriptiveAnalyticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DescriptiveAnalyticsServiceApplication.class, args);


	}

}
