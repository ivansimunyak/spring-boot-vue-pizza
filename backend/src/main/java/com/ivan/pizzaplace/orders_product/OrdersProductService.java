package com.ivan.pizzaplace.orders_product;

import com.ivan.pizzaplace.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersProductService {
    private final OrdersProductRepository ordersProductRepository;

    @Autowired
    public OrdersProductService(OrdersProductRepository ordersProductRepository) {
        this.ordersProductRepository = ordersProductRepository;
    }

    public List<OrdersProduct> getOrdersProduct() {
        return ordersProductRepository.findAll();
    }

    public void insertNewOrdersProduct(OrdersProduct ordersProduct) {
        Optional<OrdersProduct> optionalOrdersProduct = ordersProductRepository.findOrdersProductById(ordersProduct.getOrdersProductId().getOrdersId(), ordersProduct.getOrdersProductId().getProductId());
        if (optionalOrdersProduct.isPresent()) {
            throw new IllegalStateException("Error id exists!");
        }
        System.out.println(ordersProduct);
        ordersProductRepository.save(ordersProduct);
    }

    public void updateOrdersProduct(OrdersProduct ordersProduct) {
        Optional<OrdersProduct> optionalOrdersProduct = ordersProductRepository.findOrdersProductById(ordersProduct.getOrdersProductId().getOrdersId(), ordersProduct.getOrdersProductId().getProductId());
        if (optionalOrdersProduct.isPresent()) {
            ordersProductRepository.save(ordersProduct);
        } else throw new IllegalStateException("Record does not exist or id is not provided!");
    }

    public void deleteOrdersProduct(OrdersProduct ordersProduct) {
        Optional<OrdersProduct> optionalOrdersProduct = ordersProductRepository.findOrdersProductById(ordersProduct.getOrdersProductId().getOrdersId(), ordersProduct.getOrdersProductId().getProductId());
        if (optionalOrdersProduct.isPresent()) {
            ordersProductRepository.delete(ordersProduct);
        } else throw new IllegalStateException("Record does not exist or id is not provided!");
    }

    public List<OrdersProduct> getOrdersProduct(Long id) {
        List<OrdersProduct> ordersProduct = ordersProductRepository.findOrdersProductByOrderId(id);
        if (!ordersProduct.isEmpty()) {
            return ordersProduct;
        } else {
            throw new IllegalStateException("User not found!");
        }
    }
}
