package com.ivan.pizzaplace.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getReviews() {
        return reviewService.getReviews();
    }

    @PostMapping(path = "/addreview")
    public void addReview(@RequestBody Review review) {
        reviewService.insertNewReview(review);
    }

    @PostMapping(path = "/deletereview")
    public void deleteReview(@RequestParam Long id) {
        reviewService.removeReview(id);
    }


}
