package com.yogo.orderstransactionservice.controler;

import com.yogo.orderstransactionservice.domain.CustomerOrder;
import com.yogo.orderstransactionservice.repo.CustomerOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderControler {
    @Autowired
    private CustomerOrderRepo orderRepo;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<CustomerOrder> getAll(){
        return orderRepo.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CustomerOrder> getById(@RequestParam("id") String id){
        return orderRepo.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@RequestBody CustomerOrder order){
        orderRepo.save(order).subscribe();
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrders(@RequestBody List<CustomerOrder> orderList){
        orderRepo.saveAll(orderList).subscribe();
    }

}
