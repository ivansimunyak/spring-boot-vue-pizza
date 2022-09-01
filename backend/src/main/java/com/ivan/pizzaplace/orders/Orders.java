package com.ivan.pizzaplace.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivan.pizzaplace.orders_product.OrdersProduct;
import com.ivan.pizzaplace.review.Review;
import com.ivan.pizzaplace.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = "Orders")
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;
    private String status;
    private String adress;
    private String phoneNumber;
    private LocalDateTime orderDate;
    private LocalDateTime shippingDate;
    private String customerComment;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrdersProduct> ordersProducts;

    public Orders() {
    }

    public Orders(User customer,
                  String status,
                  String adress,
                  String phoneNumber,
                  LocalDateTime orderDate,
                  LocalDateTime shippingDate,
                  String customerComment,
                  String firstName,
                  String lastName,
                  List<OrdersProduct> ordersProducts) {
        this.customer = customer;
        this.status = status;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.customerComment = customerComment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ordersProducts=ordersProducts;
    }

    public Orders(Long id,
                  User customer,
                  String status,
                  String adress,
                  String phoneNumber,
                  LocalDateTime orderDate,
                  LocalDateTime shippingDate,
                  String customerComment,
                  String firstName,
                  String lastName,
                  List<OrdersProduct> ordersProducts) {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.customerComment = customerComment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ordersProducts=ordersProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
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

    public List<OrdersProduct> getOrdersProducts() {
        return ordersProducts;
    }

    public void setOrdersProducts(List<OrdersProduct> ordersProducts) {
        this.ordersProducts = ordersProducts;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderDate=" + orderDate +
                ", shippingDate=" + shippingDate +
                ", customerComment='" + customerComment + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ordersProducts=" + ordersProducts +
                '}';
    }
}
