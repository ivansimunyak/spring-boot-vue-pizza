package com.ivan.pizzaplace.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProductCategories() {
        return productService.getProducts();
    }

    @PostMapping(path = "/addproduct")
    public void addProduct(@RequestBody Product product) {
        productService.insertNewProduct(product);
    }

    @PostMapping(path = "/updateproduct")
    public void updateExistingCategory(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @PostMapping(path = "/deleteproduct")
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
    }

}
