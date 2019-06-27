package com.yogo.mysqltranservice.repo;

import com.yogo.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;


public interface OrderItemRepo extends CrudRepository<OrderItem,String> {
}
