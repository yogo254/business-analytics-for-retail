package com.yogo.descriptiveanalyticsservice.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SparkBeans {

@Bean
    public SparkSession getSparkSessioin(){
        return SparkSession.builder().appName("analytics").master("local").getOrCreate();
    }
}
