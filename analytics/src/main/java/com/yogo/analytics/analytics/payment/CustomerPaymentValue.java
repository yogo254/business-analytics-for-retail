package com.yogo.analytics.analytics.payment;

public class CustomerPaymentValue {
    private String customerId;
    private Double value;

    public CustomerPaymentValue() {
    }

    public CustomerPaymentValue(String customerId, Double value) {
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

    @Override
    public String toString() {
        return "CustomerPaymentValue{" +
                "customerId='" + customerId + '\'' +
                ", value=" + value +
                '}';
    }
}
