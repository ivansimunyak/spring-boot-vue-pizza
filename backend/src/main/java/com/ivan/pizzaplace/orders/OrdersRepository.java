package com.ivan.pizzaplace.orders;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.id=?1")
    Orders findOrdersById(Long id);

    @Query("SELECT o FROM Orders o WHERE o.customer.username=?1")
    List<Orders> findOrdersByCustomer(String username);

    Optional<Orders> findFirstByCustomerIdOrderByOrderDateDesc(Long id);


}
