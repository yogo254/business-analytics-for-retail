package com.yogo.mysqltranservice.repo;

import com.yogo.domain.CustomerOrder;
import org.springframework.data.repository.CrudRepository;


public interface CustomerOrderRepo extends CrudRepository<CustomerOrder,String> {
}
