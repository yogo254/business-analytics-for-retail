package com.yogo.analytics.analytics.customer;

import com.yogo.analytics.util.DoubleStats;

import java.util.List;

public class CustomerCounts {
    private List<String> resentCustomersIds;
    private DoubleStats statistics;


    public CustomerCounts() {
    }

    public CustomerCounts(List<String> resentCustomersIds, DoubleStats statistics) {
        this.resentCustomersIds = resentCustomersIds;
        this.statistics = statistics;
    }

    public List<String> getResentCustomersIds() {
        return resentCustomersIds;
    }

    public void setResentCustomersIds(List<String> resentCustomersIds) {
        this.resentCustomersIds = resentCustomersIds;
    }

    public DoubleStats getStatistics() {
        return statistics;
    }

    public void setStatistics(DoubleStats statistics) {
        this.statistics = statistics;
    }
}
