package com.yogo.sellersproductsservice.controler;

import com.yogo.sellersproductsservice.domain.Product;
import com.yogo.sellersproductsservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getAll(){
        return productRepo.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product>getById(@RequestParam("id") String id){
        return productRepo.findById(id);
    }
    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> getByCategory(@RequestParam("category") String category){
        return productRepo.findProductsByProductCategory(category);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@RequestBody Product product){
        productRepo.save(product).subscribe();

    }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAllProducts(@RequestBody List<Product> products){
        productRepo.saveAll(products).subscribe();

    }
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product){
        productRepo.save(product).subscribe();
    }






}
