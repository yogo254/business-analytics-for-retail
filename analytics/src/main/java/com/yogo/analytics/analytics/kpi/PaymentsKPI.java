package com.yogo.analytics.analytics.kpi;

import com.yogo.analytics.domain.Payment;
import com.yogo.analytics.entity.PaymentTransaction;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class PaymentsKPI {
    public LongSummaryStatistics intallmentStats(List<PaymentTransaction> paymentTransactions){
        List<Payment> payments=new ArrayList<>();
        paymentTransactions.stream()
                .forEach(p->payments.add(p.getPayments()));

        return payments.stream()
                .collect(Collectors.groupingBy(Payment::getOrderId,Collectors.counting()))
                .entrySet()
                .stream()
                .map(IntalmentCount::new)
                .collect(Collectors.summarizingLong(IntalmentCount::getCount));
    }
}
