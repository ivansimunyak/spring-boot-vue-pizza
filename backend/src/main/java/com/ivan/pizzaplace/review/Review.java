package com.ivan.pizzaplace.review;

import com.ivan.pizzaplace.user.User;

import javax.persistence.*;

@Entity(name = "Review")
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;
    private int rating;
    private String comment;

    public Review() {
    }

    public Review(User reviewer, int rating, String comment) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
    }

    public Review(Long id, User reviewer, int rating, String comment) {
        this.id = id;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewer=" + reviewer +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
