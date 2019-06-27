package com.yogo.orderstransactionservice.controler;

import com.yogo.orderstransactionservice.domain.OrderItem;
import com.yogo.orderstransactionservice.repo.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("api/orderitems")
public class OrderItemControler {
    @Autowired
    private OrderItemRepo itemRepo;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<OrderItem>getAll(){
       return itemRepo.findAll();
    }
    @GetMapping("/orderid")
    @ResponseStatus(HttpStatus.OK)
    public Flux<OrderItem> getByOrderId(@RequestParam("orderid") String orderID){
        return itemRepo.findOrderItemsByOrderid(orderID);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrderItem(@RequestBody OrderItem item){
        itemRepo.save(item).subscribe();
    }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrderItems(@RequestBody List<OrderItem> item){
        itemRepo.saveAll(item).subscribe();
    }




}
