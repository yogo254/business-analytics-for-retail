package com.yogo.analytics.analytics.kpi;

import java.util.Map;

public class IntalmentCount {
    private String OrderId;
    private Long count;

    public IntalmentCount(Map.Entry<String, Long> stringLongEntry) {
        this.count=stringLongEntry.getValue();
        this.OrderId=stringLongEntry.getKey();
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
