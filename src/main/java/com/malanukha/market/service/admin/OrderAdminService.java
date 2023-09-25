package com.malanukha.market.service.admin;

import com.malanukha.market.domain.shopping.Order;
import com.malanukha.market.domain.shopping.OrderPayment;
import com.malanukha.market.domain.shopping.OrderPaymentStatus;
import com.malanukha.market.domain.user.User;
import com.malanukha.market.domain.user.UserAddress;
import com.malanukha.market.domain.user.UserPayment;
import com.malanukha.market.dto.OrderDto;
import com.malanukha.market.repository.shopping.OrderRepository;
import com.malanukha.market.repository.user.UserAddressRepository;
import com.malanukha.market.repository.user.UserPaymentRepository;
import com.malanukha.market.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminService extends BaseAdminService<OrderDto, Order> {

    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserPaymentRepository userPaymentRepository;

    public OrderAdminService(OrderRepository repository, UserRepository userRepository, UserAddressRepository userAddressRepository,
                             UserPaymentRepository userPaymentRepository) {
        super(repository);
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
        this.userPaymentRepository = userPaymentRepository;
    }

    @Override
    protected Order convertFromDto(OrderDto dto) {
        User user = userRepository.findByUsername(dto.getUsername());
        OrderPayment orderPayment = OrderPayment.builder()
                .userPayment(stringToUserPayment(dto.getUserPayment()))
                .amount(dto.getAmountPayed())
                .paymentStatus(OrderPaymentStatus.valueOf(dto.getPaymentStatus()))
                .build();
        return Order.builder()
                .user(user)
                .total(dto.getOrderTotal())
                .orderPayment(orderPayment)
                .userAddress(stringToUserAddress(dto.getUserAddress()))
                .build();
    }

    @Override
    protected OrderDto convertToDto(Order entity) {
        OrderPayment orderPayment = entity.getOrderPayment();
        return OrderDto.builder()
                .id(entity.getId())
                .username(entity.getUser().getUsername())
                .orderTotal(entity.getTotal())
                .userAddress(entity.getUserAddress().toString())
                .userPayment(entity.getOrderPayment().getUserPayment().toString())
                .amountPayed(orderPayment.getAmount())
                .paymentStatus(orderPayment.getPaymentStatus().name())
                .build();
    }

    private UserAddress stringToUserAddress(String str) {
        Long id = Long.parseLong(str.split(";")[4]);
        return userAddressRepository.findById(id).orElseThrow();
    }

    private UserPayment stringToUserPayment(String str) {
        Long id = Long.parseLong(str.split(";")[2]);
        return userPaymentRepository.findById(id).orElseThrow();
    }
}