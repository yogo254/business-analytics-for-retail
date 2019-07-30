package com.yogo.analytics.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.analytics.analytics.payment.CustomerPaymentValue;
import com.yogo.analytics.analytics.payment.PaymentAnalytics;
import com.yogo.analytics.analytics.payment.PaymentMethodCount;
import com.yogo.analytics.entity.PaymentTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PaymentsJob implements Runnable {
    @Autowired
    private KafkaTemplate<String,String>kafkaTemplate;
    private List<PaymentTransaction> paymentTransactions=new ArrayList<>();
    @Autowired
    private PaymentAnalytics paymentAnalytics;
    @Autowired
    private ObjectMapper mapper;


    @Override
    public void run() {

       List<PaymentMethodCount>counts=paymentAnalytics.getMethodCounts(paymentTransactions);


//
//        kafkaTemplate.send("paymentmethod",getJsonString(counts));

          counts.stream().forEach(System.out::println);

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

    public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
    }
}
