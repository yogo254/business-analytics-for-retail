package com.yogo.datalakemasterservice.repo;


import com.yogo.datalakemasterservice.domain.Payment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PaymentRepo extends ReactiveMongoRepository<Payment,String> {

    Flux<Payment> findPaymentsByOrderId(String orderId);
}
