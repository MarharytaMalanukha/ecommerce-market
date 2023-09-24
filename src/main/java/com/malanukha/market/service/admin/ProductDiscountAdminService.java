package com.malanukha.market.service.admin;

import com.malanukha.market.domain.product.ProductDiscount;
import com.malanukha.market.dto.ProductDiscountDto;
import com.malanukha.market.repository.product.ProductDiscountRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDiscountAdminService extends BaseAdminService<ProductDiscountDto, ProductDiscount> {

    public ProductDiscountAdminService(ProductDiscountRepository repository) {
        super(repository);
    }

    @Override
    protected ProductDiscount convertFromDto(ProductDiscountDto dto) {
        return ProductDiscount.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .discountPercent(dto.getDiscountPercent())
                .active(dto.getActive().equals("Yes"))
                .build();
    }

    @Override
    protected ProductDiscountDto convertToDto(ProductDiscount entity) {
        return ProductDiscountDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .discountPercent(entity.getDiscountPercent())
                .active(entity.isActive() ? "Yes" : "No")
                .build();
    }
}
