package com.malanukha.market.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderDto {

    private Long id;
    private String username;
    private BigDecimal orderTotal;
    private String userAddress;
    private String userPayment;
    private BigDecimal amountPayed;
    private String paymentStatus;
}