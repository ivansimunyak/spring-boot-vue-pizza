package com.ivan.pizzaplace.product_category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT pc FROM ProductCategory pc WHERE pc.name=?1")
    Optional<ProductCategory> findProductCategoryByName(String name);

    @Query("SELECT pc FROM ProductCategory pc WHERE pc.id=?1")
    Optional<ProductCategory> findProductCategoryById(Long id);
}
