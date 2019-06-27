package com.yogo.orderstransactionservice.repo;

import com.yogo.orderstransactionservice.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface CustomerRepo extends ReactiveMongoRepository<Customer,String> {


}
