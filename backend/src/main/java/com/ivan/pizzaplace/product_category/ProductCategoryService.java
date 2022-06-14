package com.ivan.pizzaplace.product_category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }

    public void insertNewProductCategory(ProductCategory productCategory) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findProductCategoryByName(productCategory.getName());
        if (optionalProductCategory.isPresent()) {
            throw new IllegalStateException("Name exists already!");
        }
        productCategoryRepository.save(productCategory);
    }

    public void updateProductCategory(ProductCategory productCategory) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findProductCategoryById(productCategory.getId());
        if (optionalProductCategory.isPresent()) {
            productCategoryRepository.save(productCategory);
        } else throw new IllegalStateException("Product category does not exist or id is not provided!");
    }

    public void deleteProductCategory(ProductCategory productCategory) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findProductCategoryById(productCategory.getId());
        if (optionalProductCategory.isPresent()) {
            productCategoryRepository.delete(productCategory);
        } else throw new IllegalStateException("Product category does not exist or id is not provided!");
    }

}
