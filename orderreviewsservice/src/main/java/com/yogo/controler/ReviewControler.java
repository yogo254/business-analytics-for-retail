package com.yogo.controler;

import com.yogo.domain.Review;
import com.yogo.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewControler {
    @Autowired
    private ReviewRepo reviewRepo;

    @GetMapping("/all")
    public Flux<Review>getAll(){
        return reviewRepo.findAll();
    }
    @GetMapping("/{id}")
    public Flux<Review>getByOrderId(@PathVariable("id")String id){
        return reviewRepo.findAllByOrderId(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addReview(@RequestBody Review review){
        reviewRepo.save(review).subscribe();
    }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void addAll(@RequestBody List<Review>reviews){
        reviewRepo.saveAll(reviews).subscribe();
    }
}
