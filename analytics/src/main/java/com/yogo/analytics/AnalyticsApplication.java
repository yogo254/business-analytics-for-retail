package com.yogo.analytics;


import com.yogo.analytics.component.CategoryBuffer;
import com.yogo.analytics.util.CategoryFinder;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication


public class AnalyticsApplication implements ApplicationRunner {

@Autowired
private AdminClient admin;
   public static void main(String[] args) {
        SpringApplication.run(AnalyticsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        admin.createTopics(Arrays.asList(new NewTopic("customers",1, (short) 1),
                new NewTopic("products",1, (short) 1)));



    }
}


