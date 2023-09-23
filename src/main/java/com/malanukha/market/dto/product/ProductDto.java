package com.malanukha.market.dto.product;

import com.malanukha.market.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public ProductDto(Product product) {
        id = product.getId();
        sku = product.getSku();
        name = product.getName();
        description = product.getDescription();
        imageUrl = product.getImageUrl();
        priceEuro = product.getPriceEuro();
        quantity = product.getQuantity();
        category = product.getProductCategory().getName();
        discount = product.getProductDiscount().getName();
    }
}