package com.ivan.pizzaplace.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public List<Orders> getOrders() {
        return ordersService.getOrders();
    }

    @PostMapping(path = "/addorder")
    public void addBorder(@RequestBody Orders orders) {
        ordersService.insertNewOrder(orders);
    }

    @PostMapping(path = "/updateorder")
    public void updateOrder(@RequestBody Orders orders) {
        ordersService.updateOrders(orders);
    }

    @DeleteMapping(path = "/deleteorder")
    public void deleteOrder(@RequestBody Orders order) {
        ordersService.deleteOrders(order);
    }
}
