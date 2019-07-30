package com.yogo.datalakemasterservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    @Id

    private String id;
    @Field("order_id")
    private String orderId;
    @Field("Payment_sequencial")
    private Integer paymentSequencial;
    @Field("payment_type")
    private String paymentType;
    @Field("paymentInstalment")
    private Integer paymentInstalment;
    @Field("value")

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
