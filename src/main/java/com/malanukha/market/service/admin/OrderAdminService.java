package com.malanukha.market.service.admin;

import com.malanukha.market.domain.shopping.Order;
import com.malanukha.market.domain.shopping.OrderPayment;
import com.malanukha.market.domain.shopping.OrderPaymentStatus;
import com.malanukha.market.domain.user.User;
import com.malanukha.market.dto.product.OrderDto;
import com.malanukha.market.repository.shopping.OrderRepository;
import com.malanukha.market.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminService extends BaseAdminService<OrderDto, Order> {

    private final UserRepository userRepository;

    public OrderAdminService(OrderRepository repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    @Override
    protected Order convertFromDto(OrderDto dto) {
        OrderPayment orderPayment = OrderPayment.builder()
                .provider(dto.getProvider())
                .amount(dto.getAmountPayed())
                .paymentStatus(OrderPaymentStatus.valueOf(dto.getPaymentStatus()))
                .build();
        User user = userRepository.findByUsername(dto.getUsername());
        return Order.builder()
                .user(user)
                .total(dto.getOrderTotal())
                .orderPayment(orderPayment)
                .build();
    }

    @Override
    protected OrderDto convertToDto(Order entity) {
        OrderPayment orderPayment = entity.getOrderPayment();
        return OrderDto.builder()
                .id(entity.getId())
                .username(entity.getUser().getUsername())
                .orderTotal(entity.getTotal())
                .provider(orderPayment.getProvider())
                .amountPayed(orderPayment.getAmount())
                .paymentStatus(orderPayment.getPaymentStatus().name())
                .build();
    }
}