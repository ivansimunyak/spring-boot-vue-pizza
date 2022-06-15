package com.ivan.pizzaplace.orders;


import com.ivan.pizzaplace.orders_product.OrdersProduct;
import com.ivan.pizzaplace.user.User;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    public void insertNewOrder(Orders orders) {
        Orders foundOrders = ordersRepository.findOrdersById(orders.getId());
        if (foundOrders != null) {
            throw new IllegalStateException("Error id exists!");
        }
        ordersRepository.save(orders);
    }

    public void updateOrders(Orders orders) {
        Orders foundOrders = ordersRepository.findOrdersById(orders.getId());
        if (foundOrders != null) {
            ordersRepository.save(orders);
        } else throw new IllegalStateException("Order does not exist or id is not provided!");
    }

    public void deleteOrders(Long id) {
        Orders foundOrders = ordersRepository.findOrdersById(id);
        if (foundOrders != null) {
            ordersRepository.delete(foundOrders);
        } else throw new IllegalStateException("Order does not exist or id is not provided!");
    }
    public Orders getOrder(Long id) {
        Orders foundOrders = ordersRepository.findOrdersById(id);
        if (foundOrders != null) {
            return foundOrders;
        } else {
            throw new IllegalStateException("Order not found!");
        }
    }
    public void updateOrderStatus(Orders orders) {
        Orders foundOrder = ordersRepository.findOrdersById(orders.getId());
        if (foundOrder != null) {
            foundOrder.setStatus(orders.getStatus());
            ordersRepository.save(foundOrder);
        } else throw new IllegalStateException("Order does not exist or id is not provided!");
    }
}
