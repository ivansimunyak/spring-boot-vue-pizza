package com.ivan.pizzaplace.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name=?1")
    Optional<Product> findProductByName(String name);

    @Query("SELECT p FROM Product p WHERE p.id=?1")
    Optional<Product> findProductById(Long id);
}
