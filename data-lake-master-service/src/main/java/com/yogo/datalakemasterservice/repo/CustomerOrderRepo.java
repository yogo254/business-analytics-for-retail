package com.yogo.datalakemasterservice.repo;


import com.yogo.datalakemasterservice.domain.CustomerOrder;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface CustomerOrderRepo extends ReactiveMongoRepository<CustomerOrder,String> {

    Flux<CustomerOrder> findCustomerOrdersByCustomerId(String customerId);
}
