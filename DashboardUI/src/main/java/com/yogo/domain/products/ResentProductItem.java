package com.yogo.domain.products;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ResentProductItem {
    private final SimpleStringProperty priductId;
    private final SimpleStringProperty sellerId;
    private final SimpleDoubleProperty price;
    private final SimpleStringProperty timeStamp;

    public ResentProductItem(String priductId, String sellerId, Double price, String timeStamp) {
        this.priductId = new SimpleStringProperty(priductId);
        this.sellerId = new SimpleStringProperty(sellerId);
        this.price = new SimpleDoubleProperty(price);
        this.timeStamp = new SimpleStringProperty(timeStamp);
    }

    public String getPriductId() {
        return priductId.get();
    }

    public SimpleStringProperty priductIdProperty() {
        return priductId;
    }

    public void setPriductId(String priductId) {
        this.priductId.set(priductId);
    }

    public String getSellerId() {
        return sellerId.get();
    }

    public SimpleStringProperty sellerIdProperty() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId.set(sellerId);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getTimeStamp() {
        return timeStamp.get();
    }

    public SimpleStringProperty timeStampProperty() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp.set(timeStamp);
    }
}
