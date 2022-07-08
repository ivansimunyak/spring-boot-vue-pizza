package com.ivan.pizzaplace.product;

import com.ivan.pizzaplace.product_category.ProductCategory;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String size;
    private int price;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    public Product() {
    }

    public Product(String name, String size, int price, ProductCategory productCategory, String imageUrl) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.productCategory = productCategory;
        this.imageUrl = imageUrl;
    }

    public Product(Long id, String name, String size, int price, ProductCategory productCategory, String imageUrl) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.productCategory = productCategory;
        this.imageUrl = imageUrl;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", productCategory=" + productCategory +
                '}';
    }
}
