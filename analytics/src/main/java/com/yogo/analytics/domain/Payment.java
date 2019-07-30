package com.yogo.analytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {

    private String id;

    private String orderId;

    private Integer paymentSequencial;

    private String paymentType;

    private Integer paymentInstalment;


    private Double value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPaymentSequencial() {
        return paymentSequencial;
    }

    public void setPaymentSequencial(Integer paymentSequencial) {
        this.paymentSequencial = paymentSequencial;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentInstalment() {
        return paymentInstalment;
    }

    public void setPaymentInstalment(Integer paymentInstalment) {
        this.paymentInstalment = paymentInstalment;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
