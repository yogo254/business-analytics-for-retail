package com.yogo.analytics.config;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public SparkConf getSparkConf(){
        SparkConf conf=new SparkConf()
                .setAppName("analytics")
                .setMaster("local[2]");
        return conf;
    }
    @Bean
    public JavaSparkContext getSparkContext(){
        JavaSparkContext context=new JavaSparkContext(getSparkConf());
        return context;
    }
    @Bean

    public SparkSession getSparkSession(){
        SparkSession session= SparkSession.builder()
                .appName("sqlanalytics")
                .sparkContext(getSparkContext().sc()).getOrCreate();
        return session;
    }

}
