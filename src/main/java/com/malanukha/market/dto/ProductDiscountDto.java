package com.malanukha.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDiscountDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal discountPercent;
    private String active;
}