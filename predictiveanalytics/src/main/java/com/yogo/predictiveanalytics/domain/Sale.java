package com.yogo.predictiveanalytics.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Document
public class Sale {
    @Id
    @Field("order_id")
    private String orderId;
    @Field("customer_id")
    private String customerId;
    @Field("product_id")
    private String productId;
    @Field("seller_id")
    private String sellerId;
    private Date timestamp;
    private double price;


    public Sale() {
    }

    public Sale(String orderId, String customerId, String productId, String sellerId, Date timestamp, double price) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.timestamp = timestamp;
        this.price = price;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public LocalDateTime toLocalDateTime(){
        LocalDateTime localDateTime=
                LocalDateTime
                        .ofInstant(this.getTimestamp().toInstant(), ZoneId.systemDefault());
        return localDateTime;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", timestamp=" + timestamp +
                ", price=" + price +
                '}';
    }
}
