package com.yogo.analytics.analytics.product;

public class ProductCount {
    private String productId;
    private Long count;

    public ProductCount(String productId, Long count) {
        this.productId = productId;
        this.count = count;
    }

    public ProductCount() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductCount{" +
                "productId='" + productId + '\'' +
                ", count=" + count +
                '}';
    }
}
