package com.malanukha.market.service.product;

import com.malanukha.market.domain.product.MainProductCategory;
import com.malanukha.market.repository.product.MainProductCategoryRepository;
import com.malanukha.market.repository.product.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryService {

    private MainProductCategoryRepository mainProductCategoryRepository;
    private ProductCategoryRepository productCategoryRepository;

    public List<MainProductCategory> getMainProductCategories() {
        return mainProductCategoryRepository.findAll(Pageable.ofSize(10)).toList();
    }

}
