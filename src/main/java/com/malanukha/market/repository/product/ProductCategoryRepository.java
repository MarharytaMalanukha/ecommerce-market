package com.malanukha.market.repository.product;

import com.malanukha.market.domain.product.ProductCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends BaseAdminRepository<ProductCategory, Long> {

    @Query("select p.name from ProductCategory p")
    List<String> getName();

    ProductCategory findFirstByName(String name);

    @EntityGraph(value = "ProductCategory.productCategories")
    @Query("select p from ProductCategory p where p.name = ?1")
    ProductCategory fetchProductCategoryWithProductCategories(String name);

    @EntityGraph(value = "ProductCategory.products")
    @Query("select p from ProductCategory p where p.name = ?1")
    ProductCategory fetchProductCategoryWithProducts(String name);

    @Query("select p from ProductCategory p where p.mainProductCategory is null")
    List<ProductCategory> findMainCategories();
}