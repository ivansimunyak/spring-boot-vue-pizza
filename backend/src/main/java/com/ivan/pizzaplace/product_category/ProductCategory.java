package com.ivan.pizzaplace.product_category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivan.pizzaplace.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ProductCategory")
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(Long id, String name) {
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


    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
