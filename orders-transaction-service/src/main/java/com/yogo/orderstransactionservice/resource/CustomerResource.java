package com.yogo.orderstransactionservice.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.yogo.orderstransactionservice.domain.Customer;
import org.springframework.hateoas.ResourceSupport;


public class CustomerResource extends ResourceSupport {
 private final Customer customer;
@JsonCreator
    public CustomerResource(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
