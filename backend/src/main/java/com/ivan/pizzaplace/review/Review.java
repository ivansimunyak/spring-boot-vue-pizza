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
    private String rating;
    private String comment;
    private Long order_id;

    public Review() {
    }

    public Review(User reviewer, String rating, String comment, Long order_id) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.order_id = order_id;
    }

    public Review(Long id, User reviewer, String rating, String comment, Long order_id) {
        this.id = id;
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
        this.order_id = order_id;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewer=" + reviewer +
                ", rating='" + rating + '\'' +
                ", comment='" + comment + '\'' +
                ", order_id=" + order_id +
                '}';
    }
}
