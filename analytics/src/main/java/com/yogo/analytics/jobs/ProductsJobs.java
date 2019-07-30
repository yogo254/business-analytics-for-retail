package com.yogo.analytics.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.analytics.analytics.product.*;
import com.yogo.analytics.entity.OrderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsJobs implements Runnable {
    private List<OrderTransaction> orderTransactions;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductAnalytics productAnalytics;
    @Autowired
    private KafkaTemplate<String,String>kafkaTemplate;


    @Override
    public void run() {
        ProductStatistics statistics=productAnalytics.analyse(orderTransactions);

        kafkaTemplate.send("products",getJsonString(statistics));

    }
    private String getJsonString(Object o){
        String json="";
        try {
            json=mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void setOrderTransactions(List<OrderTransaction> orderTransactions) {
        this.orderTransactions = orderTransactions;
    }
}
