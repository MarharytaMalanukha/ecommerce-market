package com.malanukha.market.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDto {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal priceEuro;
    private Integer quantity;
    private String category;
    private String discount;
}