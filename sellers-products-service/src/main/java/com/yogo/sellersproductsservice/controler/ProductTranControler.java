package com.yogo.sellersproductsservice.controler;

import com.yogo.sellersproductsservice.domain.ProductTranslation;
import com.yogo.sellersproductsservice.repo.ProductTranslationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/products/tran")
public class ProductTranControler {
    @Autowired
    private ProductTranslationRepo translationRepo;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductTranslation> getAll(){
        return translationRepo.findAll();
    }
    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductTranslation> getByCartegoryName(@RequestParam("category") String category){
        return translationRepo.findProductTranslationByCatergoryName(category);
    }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTranslations(@RequestBody List<ProductTranslation> translations){
        translationRepo.saveAll(translations).subscribe();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTranslation(@RequestBody ProductTranslation  translation){
        translationRepo.save(translation).subscribe();

    }
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateTran(@RequestBody ProductTranslation translation){
        translationRepo.save(translation).subscribe();
    }

    @PatchMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateAllTranslations(@RequestBody List<ProductTranslation> translations){
        translationRepo.saveAll(translations).subscribe();
    }




}
