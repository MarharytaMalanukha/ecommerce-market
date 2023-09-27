package com.malanukha.market.service.admin;

import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.dto.ProductCategoryDto;
import com.malanukha.market.repository.product.ProductCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryAdminService extends BaseAdminService<ProductCategoryDto, ProductCategory> {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryAdminService(ProductCategoryRepository repository) {
        super(repository);
        this.productCategoryRepository = repository;
    }

    @Override
    protected ProductCategory convertFromDto(ProductCategoryDto dto) {
        validateDto(dto);
        var categoryBuilder = ProductCategory.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .description(dto.getDescription());
        if (dto.getIsMainCategory().equals("Yes")) {
            return categoryBuilder.build();
        } else {
            var mainCategory = productCategoryRepository.findFirstByName(dto.getMainCategoryName());
            return categoryBuilder
                    .mainProductCategory(mainCategory)
                    .build();
        }
    }

    @Override
    protected ProductCategoryDto convertToDto(ProductCategory entity) {
        validateEntity(entity);
        var categoryDtoBuilder = ProductCategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription());
        if (entity.getMainProductCategory() == null) {
            return categoryDtoBuilder
                    .isMainCategory("Yes")
                    .mainCategoryName("")
                    .build();
        } else {
            return categoryDtoBuilder
                    .isMainCategory("No")
                    .mainCategoryName(entity.getMainProductCategory().getName())
                    .build();
        }
    }
}