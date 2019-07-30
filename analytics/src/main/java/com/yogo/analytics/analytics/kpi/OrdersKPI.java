package com.yogo.analytics.analytics.kpi;

import com.yogo.analytics.domain.OrderItem;
import com.yogo.analytics.entity.OrderTransaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class OrdersKPI {
    private DoubleSummaryStatistics orderValue=new DoubleSummaryStatistics();

    public DoubleSummaryStatistics orderByValueStat(List<OrderTransaction> orderTransactions){

        List<OrderItem>items=new ArrayList<>();
        for (OrderTransaction t:orderTransactions)
            for (OrderItem orderItem:t.getOrderItems())
                items.add(orderItem);

         DoubleSummaryStatistics statistics= items.stream()
                    .collect(Collectors.summarizingDouble(OrderItem::getPrice));
         orderValue.combine(statistics);
         return orderValue;

    }




}
