package com.yogo.orderstransactionservice.repo;

import com.yogo.orderstransactionservice.domain.CustomerOrder;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface CustomerOrderRepo extends ReactiveMongoRepository<CustomerOrder,String> {
}
