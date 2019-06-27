package com.yogo.orderstransactionservice.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
@Document
public class OrderItem {
    @Id
    @Field("item_id")
    private String itemId;
    @Field("order_id")
    private String orderid;
    @Field("order_item_id")
    private Integer orderItemId;
    @Field("product_id")
    private String productId;
    @Field("seller_id")
    private String sellerId;
    @Field("shipping_limit_Date")
    private Timestamp shippingLimitDate;
    @Field("price")
    private Double price;
    @Field("freight")
    private Double freightValue;

    public OrderItem() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
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

    public Timestamp getShippingLimitDate() {
        return shippingLimitDate;
    }

    public void setShippingLimitDate(Timestamp shippingLimitDate) {
        this.shippingLimitDate = shippingLimitDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFreightValue() {
        return freightValue;
    }

    public void setFreightValue(Double freightValue) {
        this.freightValue = freightValue;
    }
}
