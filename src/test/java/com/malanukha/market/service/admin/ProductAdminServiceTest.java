package com.malanukha.market.service.admin;

import com.malanukha.market.domain.product.Product;
import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.domain.product.ProductDiscount;
import com.malanukha.market.dto.ProductDto;
import com.malanukha.market.exception.DtoDoesNotExistException;
import com.malanukha.market.exception.EntityDoesNotExistException;
import com.malanukha.market.repository.product.ProductCategoryRepository;
import com.malanukha.market.repository.product.ProductDiscountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProductAdminServiceTest {

    @InjectMocks
    private ProductAdminService service;

    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private ProductDiscountRepository productDiscountRepository;

    @Test
    void convertFromDto_dtoIsCorrect_returnEntity() {
        //given
        ProductDto productDto = productDto();
        Product expectedProduct = product();

        //when
        Mockito.when(productDiscountRepository.findFirstByName("discount")).thenReturn(productDiscount());
        Mockito.when(productCategoryRepository.findFirstByName("milk products")).thenReturn(productCategory());
        Product actualProduct = service.convertFromDto(productDto);

        //then
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void convertFromDto_dtoIsNull_throwDtoDoesNotExistException() {
        assertThrows(DtoDoesNotExistException.class, () -> service.convertFromDto(null));
    }

    @Test
    void convertToDto_entityIsCorrect_returnDto() {
        //given
        Product product = product();
        ProductDto expectedDto = productDto();

        //when
        ProductDto actualProductDto = service.convertToDto(product);

        //then
        assertEquals(expectedDto, actualProductDto);
    }

    @Test
    void convertToDto_entityIsNull_throwEntityDoesNotExistException() {
        assertThrows(EntityDoesNotExistException.class, () -> service.convertToDto(null));
    }

    private ProductDto productDto() {
        return ProductDto.builder()
                .id(1L)
                .sku("XYZ12345")
                .name("milk")
                .description("description")
                .imageUrl("imageUrl")
                .priceEuro(BigDecimal.valueOf(12.34))
                .quantity(10)
                .category("milk products")
                .discount("discount")
                .build();
    }
    private Product product() {
        return Product.builder()
                .id(1L)
                .sku("XYZ12345")
                .name("milk")
                .description("description")
                .imageUrl("imageUrl")
                .priceEuro(BigDecimal.valueOf(12.34))
                .quantity(10)
                .productCategory(productCategory())
                .productDiscount(productDiscount())
                .build();
    }

    private ProductCategory productCategory() {
        return ProductCategory.builder()
                .id(1L)
                .name("milk products")
                .build();
    }

    private ProductDiscount productDiscount() {
        return ProductDiscount.builder()
                .id(1L)
                .name("discount")
                .discountPercent(BigDecimal.valueOf(50))
                .active(true)
                .build();
    }
}