package com.yogo.analytics.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.analytics.analytics.product.ProductAnalytics;
import com.yogo.analytics.analytics.product.ProductCounts;
import com.yogo.analytics.analytics.product.ProductStatistics;
import com.yogo.analytics.entity.OrderTransaction;
import com.yogo.analytics.jobs.CustomerJob;
import com.yogo.analytics.jobs.ProductsJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


@Service
public class OrdersService {
@Autowired
private ExecutorService executorService;
@Autowired
private  ObjectMapper mapper;
@Autowired
private ProductsJobs productsJobs;
@KafkaListener(topics = "orders")
public void streamingOrders(String orders){
    List<OrderTransaction>transactions=getTransaction(orders);
    productsJobs.setOrderTransactions(transactions);
    executorService.submit(productsJobs);



    }


    public List<OrderTransaction> getTransaction(String json){
        List<OrderTransaction> transactions=new ArrayList<>();
        try {
            transactions=mapper.readValue(json,mapper.getTypeFactory().constructCollectionType(List.class,OrderTransaction.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
