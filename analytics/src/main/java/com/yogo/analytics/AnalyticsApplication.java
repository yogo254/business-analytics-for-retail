package com.yogo.analytics;

import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication

public class AnalyticsApplication implements ApplicationRunner {
    @Autowired
    private JavaSparkContext context;

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        context.parallelize(Arrays.asList(1,2,3,4,5,6,7,8,9,10))
                .collect().forEach(System.out::print);

    }
}
