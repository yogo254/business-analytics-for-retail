package com.yogo.datalakemasterservice.entity;

import com.yogo.datalakemasterservice.domain.Customer;
import com.yogo.datalakemasterservice.domain.CustomerOrder;
import com.yogo.datalakemasterservice.domain.Payment;

public class PaymentTransaction {
    private CustomerOrder customerOrder;
    private Customer customer;
    private Payment payments;

    public PaymentTransaction() {
    }

    public PaymentTransaction(CustomerOrder customerOrder, Customer customer, Payment payments) {
        this.customerOrder = customerOrder;
        this.customer = customer;
        this.payments = payments;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayments() {
        return payments;
    }

    public void setPayments(Payment payments) {
        this.payments = payments;
    }
}


