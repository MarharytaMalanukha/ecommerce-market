package com.malanukha.market.repository.product;

import com.malanukha.market.domain.product.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Long> {

    @Query("select p.name from ProductDiscount p")
    List<String> getName();

    ProductDiscount findFirstByName(String name);
}