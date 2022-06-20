package com.ivan.pizzaplace.orders;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.id=?1")
    Orders findOrdersById(Long id);

    @Query("SELECT o FROM Orders o WHERE o.customer=?1")
    Optional<Orders> findOrdersByCustomer(Long id);

    Optional<Orders> findFirstByCustomerIdOrderByOrderDateDesc(Long id);


}
