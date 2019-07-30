package com.yogo.analytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Seller {

    private String sellerId;

    private String sellerCity;

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

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId='" + sellerId + '\'' +
                ", sellerCity='" + sellerCity + '\'' +
                ", sellerState='" + sellerState + '\'' +
                '}';
    }
}
