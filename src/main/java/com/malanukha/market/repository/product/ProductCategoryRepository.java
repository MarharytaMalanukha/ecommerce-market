package com.malanukha.market.repository.product;

import com.malanukha.market.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends BaseAdminRepository<ProductCategory, Long> {

    @Query("select p.name from ProductCategory p")
    List<String> getName();

    ProductCategory findFirstByName(String name);
    List<ProductCategory> findByMainProductCategoryIsNull();
}