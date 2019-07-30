package com.yogo.analytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

    private String customerId;

    private String customerUniqueId;

    private String customerZipCodePrefix;

    private String customerCity;
    private  String customerState;

    public Customer() {
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUniqueId() {
        return customerUniqueId;
    }

    public void setCustomerUniqueId(String customerUniqueId) {
        this.customerUniqueId = customerUniqueId;
    }

    public String getCustomerZipCodePrefix() {
        return customerZipCodePrefix;
    }

    public void setCustomerZipCodePrefix(String customerZipCodePrefix) {
        this.customerZipCodePrefix = customerZipCodePrefix;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerUniqueId='" + customerUniqueId + '\'' +
                ", customerZipCodePrefix='" + customerZipCodePrefix + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerState='" + customerState + '\'' +
                '}';
    }
}
