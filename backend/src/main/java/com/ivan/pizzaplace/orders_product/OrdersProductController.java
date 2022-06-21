package com.ivan.pizzaplace.orders_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/ordersproduct")
public class OrdersProductController {
    private final OrdersProductService ordersProductService;

    @Autowired
    public OrdersProductController(OrdersProductService ordersProductService) {
        this.ordersProductService = ordersProductService;
    }

    @GetMapping
    public List<OrdersProduct> getOrdersProduct() {
        return ordersProductService.getOrdersProduct();
    }

    @GetMapping(path = "/getordersproduct/")
    public List<OrdersProduct> getUser(@RequestParam Long id) {
        return ordersProductService.getOrdersProduct(id);
    }

    @PostMapping(path = "/addordersproduct")
    public void addOrdersProduct(@RequestBody List<OrdersProduct> ordersProduct) {
        ordersProductService.insertNewOrdersProduct(ordersProduct);
    }

    @PostMapping(path = "/updateordersproduct")
    public void updateOrdersProduct(@RequestBody OrdersProduct ordersProduct) {
        ordersProductService.updateOrdersProduct(ordersProduct);
    }

    @DeleteMapping(path = "/deleteordersproduct")
    public void deleteOrdersProduct(@RequestBody OrdersProduct ordersProduct) {
        ordersProductService.deleteOrdersProduct(ordersProduct);
    }
}
