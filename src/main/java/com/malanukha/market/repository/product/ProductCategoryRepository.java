package com.malanukha.market.repository.product;

import com.malanukha.market.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}