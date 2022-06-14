package com.ivan.pizzaplace.orders_product;

import com.ivan.pizzaplace.orders.Orders;
import com.ivan.pizzaplace.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "orders_product")
public class OrdersProduct {
    @EmbeddedId
    private OrdersProductId ordersProductId;
    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, insertable = false)
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    private Product product;
    private int quantity;

    public OrdersProduct(Orders orders, Product product, int quantity) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }

    public OrdersProduct(OrdersProductId ordersProductId, Orders orders, Product product, int quantity) {
        this.ordersProductId = ordersProductId;
        this.orders = orders;
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
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
                ", orders=" + orders +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
