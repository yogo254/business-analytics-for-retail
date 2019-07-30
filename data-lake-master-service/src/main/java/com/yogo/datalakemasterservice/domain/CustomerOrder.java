package com.yogo.datalakemasterservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerOrder {
    @Id
    @Field("order_id")
    private String orderId;
    @Field("customer_id")
    private String customerId;
    @Field("order_status")
    private String orderStatus;
    @Field("order_purchase_timestamp")
    private Date orderPurchaseTimestamp;
    @Field("order_approved_at")
    private Date orderApprovedAt;
    @Field("order_delivered_carrier_date")
    private Date orderDeliveredCarrierDate;
    @Field("order_delivered_customer_date")
    private Date orderDeliveredCustomerDate;
    @Field("order_estimated_delivery_date")
    private Date orderEstimatedDeliveryDate;

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

    public Date getOrderPurchaseTimestamp() {
        return orderPurchaseTimestamp;
    }

    public void setOrderPurchaseTimestamp(Date orderPurchaseTimestamp) {
        this.orderPurchaseTimestamp = orderPurchaseTimestamp;
    }

    public Date getOrderApprovedAt() {
        return orderApprovedAt;
    }

    public void setOrderApprovedAt(Date orderApprovedAt) {
        this.orderApprovedAt = orderApprovedAt;
    }

    public Date getOrderDeliveredCarrierDate() {
        return orderDeliveredCarrierDate;
    }

    public void setOrderDeliveredCarrierDate(Date orderDeliveredCarrierDate) {
        this.orderDeliveredCarrierDate = orderDeliveredCarrierDate;
    }

    public Date getOrderDeliveredCustomerDate() {
        return orderDeliveredCustomerDate;
    }

    public void setOrderDeliveredCustomerDate(Date orderDeliveredCustomerDate) {
        this.orderDeliveredCustomerDate = orderDeliveredCustomerDate;
    }

    public Date getOrderEstimatedDeliveryDate() {
        return orderEstimatedDeliveryDate;
    }

    public void setOrderEstimatedDeliveryDate(Date orderEstimatedDeliveryDate) {
        this.orderEstimatedDeliveryDate = orderEstimatedDeliveryDate;
    }
}
