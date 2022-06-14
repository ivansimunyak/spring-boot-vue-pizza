package com.ivan.pizzaplace.orders;

import com.ivan.pizzaplace.user.User;

import javax.persistence.*;
import java.util.Date;

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
    private Date orderDate;
    private Date shippingDate;
    private String customerComment;
    private String firstName;
    private String lastName;

    public Orders() {
    }

    public Orders(User customer,
                  String status,
                  String adress,
                  String phoneNumber,
                  Date orderDate,
                  Date shippingDate,
                  String customerComment,
                  String firstName,
                  String lastName) {
        this.customer = customer;
        this.status = status;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.customerComment = customerComment;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Orders(Long id,
                  User customer,
                  String status,
                  String adress,
                  String phoneNumber,
                  Date orderDate,
                  Date shippingDate,
                  String customerComment,
                  String firstName,
                  String lastName) {
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
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
                '}';
    }
}
