package com.yogo.predictiveanalytics.repo;

import com.yogo.predictiveanalytics.domain.Sale;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SalesRepo extends ReactiveMongoRepository<Sale,String> {
}
