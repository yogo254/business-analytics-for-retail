package com.yogo.analytics.analytics.payment;

import com.yogo.analytics.domain.Payment;
import com.yogo.analytics.entity.PaymentTransaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentAnalytics {
    public List<PaymentMethodCount>getMethodCounts(List<PaymentTransaction>paymentTransactions){
        List<Payment> payments=new ArrayList<>();
        paymentTransactions.stream()
                .forEach(p->payments.add(p.getPayments()));

         Map<String,Long> map= payments.stream()
                .collect(Collectors.groupingBy(Payment::getPaymentType,Collectors.counting()));

         List<PaymentMethodCount>counts=new ArrayList<>();
         map.entrySet()
                 .stream()
                 .forEach(entry -> counts.add(new PaymentMethodCount(entry.getKey(),entry.getValue())) );

         return counts;
    }






}
