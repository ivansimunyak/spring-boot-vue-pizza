package com.ivan.pizzaplace.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void insertNewProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findProductByName(product.getName());
        if (optionalProduct.isPresent()) {
            throw new IllegalStateException("Name exists already!");
        }
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findProductById(product.getId());
        if (optionalProduct.isPresent()) {
            productRepository.save(product);
        } else throw new IllegalStateException("Product does not exist or id is not provided!");
    }

    public void deleteProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findProductById(product.getId());
        if (optionalProduct.isPresent()) {
            productRepository.delete(product);
        } else throw new IllegalStateException("Product does not exist or id is not provided!");
    }
}
