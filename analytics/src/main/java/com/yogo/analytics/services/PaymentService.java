package com.yogo.analytics.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.analytics.entity.PaymentTransaction;
import com.yogo.analytics.jobs.PaymentsJob;
import com.yogo.analytics.jobs.PaymentsKPIJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class PaymentService {
    @Autowired
    private ExecutorService executorService;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PaymentsJob paymentsJob;
    @Autowired
    private PaymentsKPIJob paymentsKPIJob;

 //   @KafkaListener(topics = "payments")
    public void receive(String message){
       List<PaymentTransaction> paymentTransactions=
              getTransaction(message);

       paymentsJob.setPaymentTransactions(paymentTransactions);
       executorService.submit(paymentsJob);





    }

    public List<PaymentTransaction> getTransaction(String json){
        List<PaymentTransaction> transactions=new ArrayList<>();
        try {
            transactions=mapper.readValue(json,mapper.getTypeFactory().constructCollectionType(List.class, PaymentTransaction.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }



}
