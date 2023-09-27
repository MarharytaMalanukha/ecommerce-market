package com.malanukha.market.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductCategoryDto {

    private Long id;
    private String name;
    private String description;
    private String isMainCategory;
    private String mainCategoryName;
}