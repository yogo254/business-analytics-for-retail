package com.yogo.analytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerOrder {

    private String orderId;
    private String customerId;
    private String orderStatus;
    private Timestamp orderPurchaseTimestamp;
    private Timestamp orderApprovedAt;
    private  Timestamp orderDeliveredCarrierDate;
    private Timestamp orderDeliveredCustomerDate;
    private Timestamp orderEstimatedDeliveryDate;

    public CustomerOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderPurchaseTimestamp() {
        return orderPurchaseTimestamp;
    }

    public void setOrderPurchaseTimestamp(Timestamp orderPurchaseTimestamp) {
        this.orderPurchaseTimestamp = orderPurchaseTimestamp;
    }

    public Timestamp getOrderApprovedAt() {
        return orderApprovedAt;
    }

    public void setOrderApprovedAt(Timestamp orderApprovedAt) {
        this.orderApprovedAt = orderApprovedAt;
    }

    public Timestamp getOrderDeliveredCarrierDate() {
        return orderDeliveredCarrierDate;
    }

    public void setOrderDeliveredCarrierDate(Timestamp orderDeliveredCarrierDate) {
        this.orderDeliveredCarrierDate = orderDeliveredCarrierDate;
    }

    public Timestamp getOrderDeliveredCustomerDate() {
        return orderDeliveredCustomerDate;
    }

    public void setOrderDeliveredCustomerDate(Timestamp orderDeliveredCustomerDate) {
        this.orderDeliveredCustomerDate = orderDeliveredCustomerDate;
    }

    public Timestamp getOrderEstimatedDeliveryDate() {
        return orderEstimatedDeliveryDate;
    }

    public void setOrderEstimatedDeliveryDate(Timestamp orderEstimatedDeliveryDate) {
        this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderPurchaseTimestamp=" + orderPurchaseTimestamp +
                ", orderApprovedAt=" + orderApprovedAt +
                ", orderDeliveredCarrierDate=" + orderDeliveredCarrierDate +
                ", orderDeliveredCustomerDate=" + orderDeliveredCustomerDate +
                ", orderEstimatedDeliveryDate=" + orderEstimatedDeliveryDate +
                '}';
    }
}
