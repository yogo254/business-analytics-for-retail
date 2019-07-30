package com.yogo.datalakemasterservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    @Id
    @Field("customer_id")
    private String customerId;
    @Field("customer_unique_id")
    private String customerUniqueId;
    @Field("customer_zipcode_prefix")
    private String customerZipCodePrefix;
    @Field("customer_city")
    private String customerCity;
    @Field("customer_state")
    private String customerState;

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
}
