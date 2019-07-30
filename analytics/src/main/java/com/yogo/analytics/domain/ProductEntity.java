package com.yogo.analytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEntity {

    private String productId;
    private String productCategory;
    private Integer productWeight;
    private Integer productLenght;
    private Integer productHeight;
    private Integer productWidth;

    public ProductEntity() {
    }

    public ProductEntity(String productId, String productCategory, Integer productWeight, Integer productLenght, Integer productHeight, Integer productWidth) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productWeight = productWeight;
        this.productLenght = productLenght;
        this.productHeight = productHeight;
        this.productWidth = productWidth;

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Integer productWeight) {
        this.productWeight = productWeight;
    }

    public Integer getProductLenght() {
        return productLenght;
    }

    public void setProductLenght(Integer productLenght) {
        this.productLenght = productLenght;
    }

    public Integer getProductHeight() {
        return productHeight;
    }

    public void setProductHeight(Integer productHeight) {
        this.productHeight = productHeight;
    }

    public Integer getProductWidth() {
        return productWidth;
    }

    public void setProductWidth(Integer productWidth) {
        this.productWidth = productWidth;
    }
}
