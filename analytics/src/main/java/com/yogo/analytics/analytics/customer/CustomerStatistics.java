package com.yogo.analytics.analytics.customer;

import com.yogo.analytics.util.DoubleStats;

import java.util.List;

public class CustomerStatistics {

    public List<CustomerValue>topCustomers;
    public DoubleStats statistics;
    public CustomerCounts customerCounts;

    public CustomerStatistics() {
    }

    public CustomerStatistics(List<CustomerValue> topCustomers, DoubleStats statistics, CustomerCounts customerCounts) {
        this.topCustomers = topCustomers;
        this.statistics = statistics;
        this.customerCounts = customerCounts;
    }

    public List<CustomerValue> getTopCustomers() {
        return topCustomers;
    }

    public void setTopCustomers(List<CustomerValue> topCustomers) {
        this.topCustomers = topCustomers;
    }

    public DoubleStats getStatistics() {
        return statistics;
    }

    public void setStatistics(DoubleStats statistics) {
        this.statistics = statistics;
    }

    public CustomerCounts getCustomerCounts() {
        return customerCounts;
    }

    public void setCustomerCounts(CustomerCounts customerCounts) {
        this.customerCounts = customerCounts;
    }
}
