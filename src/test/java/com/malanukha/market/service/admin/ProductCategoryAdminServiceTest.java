package com.malanukha.market.service.admin;

import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.dto.ProductCategoryDto;
import com.malanukha.market.exception.DtoDoesNotExistException;
import com.malanukha.market.exception.EntityDoesNotExistException;
import com.malanukha.market.repository.product.ProductCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProductCategoryAdminServiceTest {

    @InjectMocks
    private ProductCategoryAdminService service;

    @Mock
    private ProductCategoryRepository repository;

    @Test
    void convertFromDto_dtoIsCorrectAndCategoryIsMain_returnEntityWithoutMainCategorySet() {
        ProductCategory actualCategory = service.convertFromDto(categoryDto("Yes", ""));
        assertEquals(category(null), actualCategory);
    }

    @Test
    void convertFromDto_dtoIsCorrectAndCategoryIsNotMain_returnEntityWithMainCategorySet() {
        Mockito.when(repository.findFirstByName("mainCategoryName")).thenReturn(mainCategory());
        ProductCategory actualCategory = service.convertFromDto(categoryDto("No", "mainCategoryName"));
        assertEquals(category(mainCategory()), actualCategory);
    }

    @Test
    void convertFromDto_dtoIsNull_throwDtoDoesNotExistException() {
        assertThrows(DtoDoesNotExistException.class, () -> service.convertFromDto(null));
    }

    @Test
    void convertToDto_dtoIsCorrectAndMainCategoryDoesNotExist_returnDtoWithoutMainCategorySet() {
        ProductCategoryDto actualCategoryDto = service.convertToDto(category(null));
        assertEquals(categoryDto("Yes", ""), actualCategoryDto);
    }

    @Test
    void convertToDto_dtoIsCorrectAndMainCategoryExist_returnDtoWithMainCategorySet() {
        ProductCategoryDto actualCategoryDto = service.convertToDto(category(mainCategory()));
        assertEquals(categoryDto("No", "mainCategoryName"), actualCategoryDto);
    }

    @Test
    void convertToDto_entityIsNull_throwEntityDoesNotExistException() {
        assertThrows(EntityDoesNotExistException.class, () -> service.convertToDto(null));
    }

    private ProductCategoryDto categoryDto(String isMainCategory, String mainCategoryName) {
        return ProductCategoryDto.builder()
                .id(2L)
                .name("categoryName")
                .description("description")
                .isMainCategory(isMainCategory)
                .mainCategoryName(mainCategoryName)
                .build();
    }

    private ProductCategory category(ProductCategory mainCategory) {
        return ProductCategory.builder()
                .id(2L)
                .name("categoryName")
                .description("description")
                .mainProductCategory(mainCategory)
                .build();
    }

    private ProductCategory mainCategory() {
        return ProductCategory.builder()
                .id(1L)
                .name("mainCategoryName")
                .description("description")
                .build();
    }
}
