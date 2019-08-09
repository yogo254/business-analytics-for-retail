package con.yogo.main.repo;

import con.yogo.main.domain.LocationGeo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GeoLocationRepo extends ReactiveMongoRepository<LocationGeo,String> {
Mono<LocationGeo>findFirstByZipCode(int code);
}
