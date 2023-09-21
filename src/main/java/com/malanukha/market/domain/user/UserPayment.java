package com.malanukha.market.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String paymentType;
    private String provider;
    private String accountNumber;
    private LocalDate expiry;
}