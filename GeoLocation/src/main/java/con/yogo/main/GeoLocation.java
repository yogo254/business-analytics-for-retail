package con.yogo.main;

import con.yogo.main.domain.LocationGeo;
import con.yogo.main.repo.GeoLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class GeoLocation implements ApplicationRunner {
    @Autowired
    private GeoLocationRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(GeoLocation.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    public LocationGeo getGeoLocaltion(String line) {
        String[]tokens=line.trim().split(",");
        int code=Integer.valueOf(tokens[0].replaceAll("\"",""));
        double longitude =Double.valueOf(tokens[2]);
        double latitude=Double.valueOf(tokens[1]);

        return new LocationGeo(code,latitude,longitude,tokens[3],tokens[4]);

    }
}
