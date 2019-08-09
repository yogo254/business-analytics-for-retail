package com.yogo.repo;

import com.yogo.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewRepo extends ReactiveMongoRepository<Review,String> {
Flux<Review>findAllByOrderId(String orderId);
}
