package com.yogo.sellersproductsservice.controler;

import com.yogo.sellersproductsservice.domain.Seller;
import com.yogo.sellersproductsservice.repo.SellersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;


@RestController
@RequestMapping("/api/sellers")
public class SellerControler {
    @Autowired
    private SellersRepo sellersRepo;
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Seller>getAllSellers(){
       return sellersRepo.findAll();
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Seller>getByID(@PathVariable("id") String id){
        return sellersRepo.findById(id);

    }
    @GetMapping("/city/{city}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Seller>getByCity(@PathVariable("city")String city){
        return sellersRepo.findSellersBySellerCity(city);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addSeller(@RequestBody Seller seller){
        sellersRepo.save(seller).subscribe();
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAllSellers(@RequestBody List<Seller> sellerList){
       sellersRepo.saveAll(sellerList).subscribe();
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteSeller(@RequestBody Seller seller){
        sellersRepo.delete(seller).subscribe();
            }
    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSellers(@RequestBody List<Seller> sellers){
        sellersRepo.deleteAll(sellers).subscribe();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateSeller(@RequestBody Seller seller) {
       sellersRepo.save(seller).subscribe();
    }
    @PatchMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void updateSellers(@RequestBody List<Seller>sellers){

        sellersRepo.saveAll(sellers).subscribe();
    }

}

