package com.ivan.pizzaplace.product_category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/productcategory")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getProductCategories();
    }

    @PostMapping(path = "/addproductcategory")
    public void addProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.insertNewProductCategory(productCategory);
    }

    @PostMapping(path = "/updateproductcategory")
    public void updateExistingCategory(@RequestBody ProductCategory productCategory) {
        System.out.println(productCategory.toString());
        productCategoryService.updateProductCategory(productCategory);
    }

    @PostMapping(path = "/deleteproductcategory")
    public void deleteProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.deleteProductCategory(productCategory);
    }
}
