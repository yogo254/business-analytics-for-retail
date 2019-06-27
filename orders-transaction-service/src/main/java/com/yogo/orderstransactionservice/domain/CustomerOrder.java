package com.yogo.orderstransactionservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
@Document
public class CustomerOrder {
    @Id
    @Field("order_id")
    private String orderId;
    @Field("order_status")
    private String orderStatus;
    @Field("order_purchase_timestamp")
    private Timestamp orderPurchaseTimestamp;
    @Field("order_approved_at")
    private Timestamp orderApprovedAt;
    @Field("order_delivered_carrier_date")
    private Timestamp orderDeliveredCarrierDate;
    @Field("order_delivered_customer_date")
    private Timestamp orderDeliveredCustomerDate;
    @Field("order_estimated_delivery_date")
    private Timestamp orderEstimatedDeliveryDate;

    public CustomerOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
