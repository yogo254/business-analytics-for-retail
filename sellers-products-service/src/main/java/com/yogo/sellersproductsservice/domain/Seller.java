package com.yogo.sellersproductsservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document()
public class Seller {
    @Id
    @Field("seller_id")
    private String sellerId;
    @Field("seller_city")
    private String sellerCity;
    @Field("seller_state")
    private String sellerState;

    public Seller() {
    }

    public Seller(String seller_id, String sellerCity, String sellerState) {
        this.sellerId = seller_id;
        this.sellerCity = sellerCity;
        this.sellerState = sellerState;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String seller_id) {
        this.sellerId = seller_id.trim();
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerState() {
        return sellerState;
    }

    public void setSellerState(String sellerState) {
        this.sellerState = sellerState.trim();
    }
}
