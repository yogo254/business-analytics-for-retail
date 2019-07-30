package com.yogo.sellersproductsservice.repo;

import com.yogo.sellersproductsservice.domain.ProductTranslation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductTranslationRepo extends ReactiveMongoRepository<ProductTranslation,String> {
    Mono<ProductTranslation> findProductTranslationByCatergoryName(String catergoryName);
}
