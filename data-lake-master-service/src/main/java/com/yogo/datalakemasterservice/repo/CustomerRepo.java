package com.yogo.datalakemasterservice.repo;


import com.yogo.datalakemasterservice.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface CustomerRepo extends ReactiveMongoRepository<Customer,String> {


}
