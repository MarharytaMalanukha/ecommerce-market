package com.malanukha.market.service.utils;

import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.domain.user.User;
import com.malanukha.market.repository.product.ProductCategoryRepository;
import com.malanukha.market.repository.product.ProductDiscountRepository;
import com.malanukha.market.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//todo refactor this huge copypasta class

@Service
@AllArgsConstructor
public class UtilsService {

    private ProductCategoryRepository productCategoryRepository;
    private ProductDiscountRepository productDiscountRepository;
    private UserRepository userRepository;

    public List<ProductCategory> getMainProductCategories() {
        return productCategoryRepository.findByMainProductCategoryIsNull();
    }

    public List<String> getProductCategoryNames() {
        return productCategoryRepository.getName();
    }

    public List<String> getProductDiscountNames() {
        return productDiscountRepository.getName();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}