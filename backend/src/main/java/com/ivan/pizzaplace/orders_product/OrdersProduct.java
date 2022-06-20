package com.ivan.pizzaplace.orders_product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivan.pizzaplace.orders.Orders;
import com.ivan.pizzaplace.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "orders_product")
public class OrdersProduct {
    @EmbeddedId
    private OrdersProductId ordersProductId;


    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    private Product product;
    private int quantity;

    public OrdersProduct( Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrdersProduct(OrdersProductId ordersProductId, Product product, int quantity) {
        this.ordersProductId = ordersProductId;
        this.product = product;
        this.quantity = quantity;
    }

    public OrdersProduct() {
    }

    public OrdersProductId getOrdersProductId() {
        return ordersProductId;
    }

    public void setOrdersProductId(OrdersProductId ordersProductId) {
        this.ordersProductId = ordersProductId;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrdersProduct{" +
                "ordersProductId=" + ordersProductId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
