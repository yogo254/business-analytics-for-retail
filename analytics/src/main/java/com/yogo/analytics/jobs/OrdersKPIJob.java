package com.yogo.analytics.jobs;

import com.yogo.analytics.analytics.kpi.OrdersKPI;
import com.yogo.analytics.entity.OrderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class OrdersKPIJob implements Runnable {
@Autowired
private OrdersKPI ordersKPI;
private List<OrderTransaction> transactions;

    @Override
    public void run() {

    }

    public void setTransactions(List<OrderTransaction> transactions) {
        this.transactions = transactions;
    }
}
