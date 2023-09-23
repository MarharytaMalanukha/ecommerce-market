package com.malanukha.market.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private String username;
    private BigDecimal orderTotal;
    private String provider;
    private BigDecimal amountPayed;
    private String paymentStatus;
}