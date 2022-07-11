package com.ivan.pizzaplace.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public void insertNewReview(Review review) {
        reviewRepository.save(review);
    }

    public void removeReview(Long id) {
        Review review = reviewRepository.findReviewById(id);
        if (review != null) {
            reviewRepository.delete(review);
        } else throw new IllegalStateException("Review does not exist or id is not provided!");
    }
}
