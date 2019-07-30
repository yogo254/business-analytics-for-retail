package com.yogo.analytics.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.analytics.analytics.kpi.PaymentsKPI;
import com.yogo.analytics.entity.PaymentTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;


@Service
public class PaymentsKPIJob implements Runnable {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PaymentsKPI paymentsKPI;
    private List<PaymentTransaction>paymentTransactions=new ArrayList<>();
    @Override
    public void run() {
        LongSummaryStatistics instalStats=paymentsKPI.intallmentStats(paymentTransactions);
        System.out.println(getJsonString(instalStats));

    }

    public void setPaymentTransactions(List<PaymentTransaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
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
}
