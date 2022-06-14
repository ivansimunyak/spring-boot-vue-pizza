package com.ivan.pizzaplace.orders;


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
        Optional<Orders> optionalOrders = ordersRepository.findOrdersById(orders.getId());
        if (optionalOrders.isPresent()) {
            throw new IllegalStateException("Error id exists!");
        }
        ordersRepository.save(orders);
    }

    public void updateOrders(Orders orders) {
        Optional<Orders> optionalOrders = ordersRepository.findOrdersById(orders.getId());
        if (optionalOrders.isPresent()) {
            ordersRepository.save(orders);
        } else throw new IllegalStateException("Order does not exist or id is not provided!");
    }

    public void deleteOrders(Orders orders) {
        Optional<Orders> optionalOrders = ordersRepository.findOrdersById(orders.getId());
        if (optionalOrders.isPresent()) {
            ordersRepository.delete(orders);
        } else throw new IllegalStateException("Order does not exist or id is not provided!");
    }
}
