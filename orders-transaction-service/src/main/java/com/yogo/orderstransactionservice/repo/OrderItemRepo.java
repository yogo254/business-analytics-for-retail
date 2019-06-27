package com.yogo.orderstransactionservice.repo;

import com.yogo.orderstransactionservice.domain.OrderItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderItemRepo extends ReactiveMongoRepository<OrderItem,String> {
    Flux<OrderItem> findOrderItemsByOrderid(String orderid);
}
