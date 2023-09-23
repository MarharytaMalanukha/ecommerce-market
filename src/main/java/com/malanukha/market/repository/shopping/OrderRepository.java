package com.malanukha.market.repository.shopping;

import com.malanukha.market.domain.shopping.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}