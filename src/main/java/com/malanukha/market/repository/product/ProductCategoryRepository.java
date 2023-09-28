package com.malanukha.market.repository.product;

import com.malanukha.market.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends BaseAdminRepository<ProductCategory, Long> {

    @Query("select p.name from ProductCategory p")
    List<String> getName();

    @EntityGraph(value = "graph.MainProductCategory")
    ProductCategory findFirstByName(String name);
    @Query("select p from ProductCategory p where p.mainProductCategory is null")
    List<ProductCategory> findMainCategories();
}