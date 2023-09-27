package com.malanukha.market.service.admin;

import com.malanukha.market.domain.product.ProductDiscount;
import com.malanukha.market.dto.ProductDiscountDto;
import com.malanukha.market.exception.DtoDoesNotExistException;
import com.malanukha.market.exception.EntityDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductDiscountAdminServiceTest {

    @InjectMocks
    private ProductDiscountAdminService service;

    @Test
    public void convertFromDto_dtoIsCorrectAndActive_returnEntityWithActiveTrue() {
        ProductDiscount actualProductDiscount = service.convertFromDto(productDiscountDto("Yes"));
        assertEquals(productDiscount(true), actualProductDiscount);
    }

    @Test
    public void convertFromDto_dtoIsCorrectAndActiveIsNotSet_returnEntityWithActiveFalse() {
        ProductDiscount actualProductDiscount = service.convertFromDto(productDiscountDto("IncorrectValue"));
        assertEquals(productDiscount(false), actualProductDiscount);
    }

    @Test
    public void convertFromDto_dtoIsNull_throwDtoDoesNotExistException() {
        assertThrows(DtoDoesNotExistException.class, () -> service.convertFromDto(null));
    }

    @Test
    public void convertToDto_entityIsCorrectAndActive_returnDtoWithActiveYes() {
        ProductDiscountDto actualProductDiscountDto = service.convertToDto(productDiscount(true));
        assertEquals(productDiscountDto("Yes"), actualProductDiscountDto);
    }

    @Test
    public void convertToDto_entityIsCorrectAndNonActive_returnDtoWithActiveNo() {
        ProductDiscountDto actualProductDiscountDto = service.convertToDto(productDiscount(false));
        assertEquals(productDiscountDto("No"), actualProductDiscountDto);
    }

    @Test
    public void convertToDto_entityIsNull_throwEntityDoesNotExistException() {
        assertThrows(EntityDoesNotExistException.class, () -> service.convertToDto(null));
    }

    private ProductDiscountDto productDiscountDto(String active) {
        return ProductDiscountDto.builder()
                .id(1L)
                .name("discount")
                .description("description")
                .discountPercent(BigDecimal.valueOf(50))
                .active(active)
                .build();
    }

    private ProductDiscount productDiscount(boolean active) {
        return ProductDiscount.builder()
                .id(1L)
                .name("discount")
                .description("description")
                .discountPercent(BigDecimal.valueOf(50))
                .active(active)
                .build();
    }
}