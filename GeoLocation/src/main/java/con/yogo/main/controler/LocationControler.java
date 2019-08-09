package con.yogo.main.controler;

import con.yogo.main.domain.LocationGeo;
import con.yogo.main.repo.GeoLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/geolocation")
public class LocationControler {
    @Autowired
    private GeoLocationRepo repo;
    @GetMapping("/{code}")
    public Mono<LocationGeo> getLocation(@PathVariable("code") int code){
        return repo.findFirstByZipCode(code);
    }
    @GetMapping("/all")
    public Flux<LocationGeo> getAll(){
        return repo.findAll();
    }
    @PostMapping("/all")
    public void addAll(@RequestBody List<LocationGeo> geoList){
        repo.saveAll(geoList).subscribe();
    }
    @PostMapping
    public void add(@RequestBody LocationGeo locationGeo){
        repo.save(locationGeo).subscribe();
    }
}
