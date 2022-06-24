package com.ivan.pizzaplace.orders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/getorder/")
    public Orders getOneOrder(@RequestParam Long id) {
        return ordersService.getOrder(id);
    }
    @PostMapping(path = "/addorder")
    public void addOrder(@RequestBody Orders orders) {
        ordersService.insertNewOrder(orders);
    }

    @PostMapping(path = "/updateorder")
    public void updateOrder(@RequestBody Orders orders) {
        ordersService.updateOrders(orders);
    }

    @PostMapping(path = "/updateorderstatus")
    public void updateOrderStatus(@RequestBody Orders orders) {
        ordersService.updateOrderStatus(orders);
    }

    @PostMapping(path = "/deleteorder/")
    public void deleteOrder(@RequestParam Long id) {
        ordersService.deleteOrders(id);
    }

    @GetMapping(path = "/getlatestorder/")
    public Optional<Orders> getLatestOrder(@RequestParam Long id) {
        return ordersService.findLatestOrder(id);
    }

    @GetMapping(path = "/getcustomerorders/")
    public List<Orders> getCustomersOrders(@RequestParam(value = "username") String username) {
        return ordersService.getCustomersOrders(username);
    }
}
