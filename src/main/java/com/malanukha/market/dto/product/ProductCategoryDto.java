package com.malanukha.market.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDto {

    private Long id;
    private String name;
    private String description;
    private String isMainCategory;
    private String mainCategoryName;
}