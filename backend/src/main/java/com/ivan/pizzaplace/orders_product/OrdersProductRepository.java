package com.ivan.pizzaplace.orders_product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdersProductRepository extends JpaRepository<OrdersProduct, OrdersProductId> {

    @Query("SELECT op FROM OrdersProduct op WHERE op.ordersProductId.ordersId=?1 AND op.ordersProductId.productId=?2")
    Optional<OrdersProduct> findOrdersProductById(Long id, Long productId);

    @Query("SELECT op FROM OrdersProduct op WHERE op.ordersProductId.ordersId=?1")
    List<OrdersProduct> findOrdersProductByOrderId(Long id);


}
