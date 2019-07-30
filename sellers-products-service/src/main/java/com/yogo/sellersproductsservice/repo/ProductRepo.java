package com.yogo.sellersproductsservice.repo;

import com.yogo.sellersproductsservice.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepo extends ReactiveMongoRepository<Product,String> {
    Flux<Product> findProductsByProductCategory(String productCategory);
}
