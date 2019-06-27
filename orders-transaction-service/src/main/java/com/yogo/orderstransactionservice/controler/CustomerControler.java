package com.yogo.orderstransactionservice.controler;

import com.yogo.orderstransactionservice.domain.Customer;
import com.yogo.orderstransactionservice.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
@RequestMapping("api/customers")
public class CustomerControler {
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Customer> getByID(@PathVariable("id") String id){
        return customerRepo.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer){
        customerRepo.save(customer).subscribe();

    }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomers(@RequestBody List<Customer> customers){
        customerRepo.saveAll(customers).subscribe();
    }




}
