package com.ivan.pizzaplace.orders_product;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrdersProductId implements Serializable {
    @Column(name = "order_id")
    private Long ordersId;
    @Column(name = "product_id")
    private Long productId;

    public OrdersProductId() {
    }

    public OrdersProductId(Long ordersId, Long productId) {
        this.ordersId = ordersId;
        this.productId = productId;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrdersProductId{" +
                "ordersId=" + ordersId +
                ", productId=" + productId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersProductId that = (OrdersProductId) o;
        return Objects.equals(ordersId, that.ordersId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordersId, productId);
    }
}
