package com.malanukha.market.repository.product;

import com.malanukha.market.domain.product.MainProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainProductCategoryRepository extends JpaRepository<MainProductCategory, Long> {
}