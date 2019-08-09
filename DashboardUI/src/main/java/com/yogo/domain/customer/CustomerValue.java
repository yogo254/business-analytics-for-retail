package com.yogo.domain.customer;


import com.yogo.util.DoubleStats;

public class CustomerValue {
    private String customerId;
    private DoubleStats stats;

    public CustomerValue() {
    }

    public CustomerValue(String customerId, DoubleStats stats) {
        this.customerId = customerId;
        this.stats = stats;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public DoubleStats getStats() {
        return stats;
    }

    public void setStats(DoubleStats stats) {
        this.stats = stats;
    }
}
