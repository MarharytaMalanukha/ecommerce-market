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
public class OrderDto {

    private Long id;
    private String username;
    private BigDecimal orderTotal;
    private String userAddress;
    private String userPayment;
    private BigDecimal amountPayed;
    private String paymentStatus;
}