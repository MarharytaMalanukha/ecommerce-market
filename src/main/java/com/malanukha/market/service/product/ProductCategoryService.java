package com.malanukha.market.service.product;

import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.repository.product.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getMainProductCategories() {
        return productCategoryRepository.findByMainProductCategoryIsNull();
    }

    public List<String> getProductCategoryNames() {
        return productCategoryRepository.getName();
    }
}