package com.yogo.analytics.analytics.payment;

public class PaymentMethodCount {
    private String method;
    private Long count;

    public PaymentMethodCount() {
    }

    public PaymentMethodCount(String method, Long count) {
        this.method = method;
        this.count = count;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PaymentMethodCount{" +
                "method='" + method + '\'' +
                ", count=" + count +
                '}';
    }
}
