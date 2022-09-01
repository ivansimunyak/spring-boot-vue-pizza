package com.ivan.pizzaplace.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivan.pizzaplace.orders.Orders;
import com.ivan.pizzaplace.review.Review;
import com.ivan.pizzaplace.user_type.UserType;

import javax.persistence.*;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    private String password;
    private String email;
    private String adress;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    @JsonIgnore
    private List<Orders> orders;

    public User() {
    }

    public User(String username,
                String password,
                String email,
                String adress,
                String firstName,
                String lastName,
                String phoneNumber,
                UserType userType,
                List<Review> reviews
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.adress = adress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.reviews = reviews;
    }

    public User(Long id,
                String username,
                String password,
                String email,
                String adress,
                String firstName,
                String lastName,
                String phoneNumber,
                UserType userType,
                List<Review> reviews
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.adress = adress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                ", reviews=" + reviews +
                '}';
    }
}
