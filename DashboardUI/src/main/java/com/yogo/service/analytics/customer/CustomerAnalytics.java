package com.yogo.service.analytics.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.domain.customer.CustomerStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomerAnalytics {
    @Autowired
    private ObjectMapper mapper;

    @KafkaListener(topics = "customers")
    public void receive(String message){
        CustomerStatistics statistics=deserialize(message);


    }
    private CustomerStatistics deserialize(String json){

        CustomerStatistics statistics=null;
        try {
            statistics=mapper.readValue(json,CustomerStatistics.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return statistics;
    }


}
