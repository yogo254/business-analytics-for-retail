package com.yogo.domain.products;

public class ProductValue {
    private String productId;
    private Double value;

    public ProductValue() {
    }

    public ProductValue(String productId, Double value) {
        this.productId = productId;
        this.value = value;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductValue{" +
                "productId='" + productId + '\'' +
                ", value=" + value +
                '}';
    }
}
