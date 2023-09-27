package com.malanukha.market.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDiscountDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal discountPercent;
    private String active;
}