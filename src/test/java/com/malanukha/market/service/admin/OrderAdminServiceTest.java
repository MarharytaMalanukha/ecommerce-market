package com.malanukha.market.service.admin;

import com.malanukha.market.domain.shopping.Order;
import com.malanukha.market.domain.shopping.OrderPayment;
import com.malanukha.market.domain.shopping.OrderPaymentStatus;
import com.malanukha.market.domain.user.User;
import com.malanukha.market.domain.user.UserAddress;
import com.malanukha.market.domain.user.UserPayment;
import com.malanukha.market.dto.OrderDto;
import com.malanukha.market.exception.DtoDoesNotExistException;
import com.malanukha.market.exception.EntityDoesNotExistException;
import com.malanukha.market.repository.user.UserAddressRepository;
import com.malanukha.market.repository.user.UserPaymentRepository;
import com.malanukha.market.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class OrderAdminServiceTest {

    @InjectMocks
    private OrderAdminService service;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserAddressRepository userAddressRepository;
    @Mock
    private UserPaymentRepository userPaymentRepository;

    @Test
    void convertFromDto_dtoIsCorrect_returnEntity() {
        //given
        OrderDto orderDto = orderDto();
        Order expectedOrder = order();

        //when
        Mockito.when(userRepository.findByUsername("user")).thenReturn(user());
        Mockito.when(userAddressRepository.findById(1L)).thenReturn(Optional.of(userAddress()));
        Mockito.when(userPaymentRepository.findById(1L)).thenReturn(Optional.of(userPayment()));

        Order actualOrder = service.convertFromDto(orderDto);

        //then
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void convertFromDto_dtoIsNull_throwDtoDoesNotExistException() {
        assertThrows(DtoDoesNotExistException.class, () -> service.convertFromDto(null));
    }

    @Test
    void convertToDto_entityIsCorrect_returnDto() {
        //given
        Order order = order();
        OrderDto expectedOrderDto = orderDto();

        //when
        OrderDto actualOrderDto = service.convertToDto(order);

        //then
        assertEquals(expectedOrderDto, actualOrderDto);
    }

    @Test
    void convertToDto_entityIsNull_throwEntityDoesNotExistException() {
        assertThrows(EntityDoesNotExistException.class, () -> service.convertToDto(null));
    }

    private OrderDto orderDto() {
        return OrderDto.builder()
                .id(1L)
                .username("user")
                .orderTotal(BigDecimal.valueOf(12.34))
                .userAddress("Ukraine;Kyiv;Khreshchatyk 1;01001;1")
                .userPayment("visa;PRIVATBANK;1")
                .amountPayed(BigDecimal.valueOf(12.34))
                .paymentStatus("SUCCEEDED")
                .build();
    }

    private Order order() {
        return Order.builder()
                .id(1L)
                .user(user())
                .userAddress(userAddress())
                .total(BigDecimal.valueOf(12.34))
                .orderPayment(OrderPayment.builder()
                        .paymentStatus(OrderPaymentStatus.SUCCEEDED)
                        .amount(BigDecimal.valueOf(12.34))
                        .userPayment(userPayment())
                        .build())
                .build();
    }

    private User user() {
        return User.builder()
                .id(1L)
                .username("user")
                .build();
    }

    private UserAddress userAddress() {
        return UserAddress.builder()
                .id(1L)
                .country("Ukraine")
                .city("Kyiv")
                .addressLine("Khreshchatyk 1")
                .postalCode("01001")
                .build();
    }

    private UserPayment userPayment() {
        return UserPayment.builder()
                .id(1L)
                .paymentType("visa")
                .provider("PRIVATBANK")
                .accountNumber("1111222233334444")
                .expiry(LocalDate.of(2025, 1, 1))
                .user(user())
                .build();
    }
}