package com.ivan.pizzaplace.user_type;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivan.pizzaplace.user.User;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @JsonIgnore
    private List<User> users;

    public UserType() {
    }

    public UserType(String name) {
        this.name = name;
    }

    public UserType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + users +
                '}';
    }
}
