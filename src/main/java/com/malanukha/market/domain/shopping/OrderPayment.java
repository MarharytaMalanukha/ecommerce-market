package com.malanukha.market.domain.shopping;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "orderPayment")
    private Order order;

    private BigDecimal amount;
    private String provider;

    @Enumerated(EnumType.STRING)
    private OrderPaymentStatus paymentStatus;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}