package com.malanukha.market.service.product;

import com.malanukha.market.repository.product.ProductDiscountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductDiscountService {

    private ProductDiscountRepository productDiscountRepository;

    public List<String> getProductDiscountNames() {
        return productDiscountRepository.getName();
    }
}