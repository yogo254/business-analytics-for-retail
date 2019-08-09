package com.yogo.domain.customer;

public class CustomerValues {
    private String customerId;
    private double value;

    public CustomerValues() {
    }

    public CustomerValues(String customerId, double value) {
        this.customerId = customerId;
        this.value = value;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
