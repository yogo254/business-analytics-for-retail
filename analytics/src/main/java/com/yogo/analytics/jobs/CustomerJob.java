package com.yogo.analytics.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.analytics.analytics.customer.CustomerAnalytics;
import com.yogo.analytics.analytics.customer.CustomerCounts;
import com.yogo.analytics.analytics.customer.CustomerStatistics;
import com.yogo.analytics.analytics.customer.CustomerValue;
import com.yogo.analytics.entity.OrderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerJob implements Runnable {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private KafkaTemplate<String,String>template;
    private List<OrderTransaction> transactionList;
    @Autowired
    private CustomerAnalytics customerAnalytics;
    @Override
    public void run() {
        CustomerStatistics statistics=customerAnalytics.analyse(transactionList);
        template.send("customers",getJsonString(statistics));



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

    public void setTransactionList(List<OrderTransaction> transactionList) {
        this.transactionList = transactionList;
    }
}
