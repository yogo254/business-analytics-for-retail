package com.yogo.descriptiveanalyticsservice;


import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DescriptiveAnalyticsServiceApplication implements ApplicationRunner {

@Autowired
private SparkSession session;
	public static void main(String[] args) {
		SpringApplication.run(DescriptiveAnalyticsServiceApplication.class,args);

	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		Dataset<Row>dataset=session.read().option("inferSchema",true).option("header",true).csv("olist_order_reviews_dataset.csv");

		dataset.collectAsList().stream().limit(4).forEach(System.out::println);


	}
}
