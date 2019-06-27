package com.yogo.sellersproductsservice.repo;

import com.yogo.sellersproductsservice.domain.Seller;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SellersRepo extends ReactiveCrudRepository<Seller,String> {

   Flux<Seller> findSellersBySellerCity(String sellerCity);

}
