package com.yogo.datalakemasterservice.sevices;

import com.yogo.datalakemasterservice.domain.Customer;
import com.yogo.datalakemasterservice.domain.OrderItem;
import com.yogo.datalakemasterservice.repo.CustomerRepo;
import com.yogo.datalakemasterservice.repo.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Service
public class OrderItemService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;

    public Customer getCustomer(String id){
        try {
            return customerRepo.findById(id).toFuture().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new Customer();
    }
    public List<OrderItem> getOrderItems(String orderId){
        return orderItemRepo.findOrderItemsByOrderid(orderId).toStream()
                .collect(Collectors.toList());
    }
}
