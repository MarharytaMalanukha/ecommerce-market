package com.malanukha.market.service.admin;

import com.malanukha.market.domain.product.Product;
import com.malanukha.market.domain.product.ProductDiscount;
import com.malanukha.market.dto.ProductDto;
import com.malanukha.market.repository.product.ProductCategoryRepository;
import com.malanukha.market.repository.product.ProductDiscountRepository;
import com.malanukha.market.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductAdminService extends BaseAdminService<ProductDto, Product> {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductDiscountRepository productDiscountRepository;

    public ProductAdminService(ProductRepository repository,
                               ProductCategoryRepository productCategoryRepository,
                               ProductDiscountRepository productDiscountRepository) {
        super(repository);
        this.productCategoryRepository = productCategoryRepository;
        this.productDiscountRepository = productDiscountRepository;
    }

    @Override
    protected Product convertFromDto(ProductDto dto) {
        ProductDiscount productDiscount = null;
        if (!dto.getDiscount().isEmpty()) {
            productDiscount = productDiscountRepository.findFirstByName(dto.getDiscount());
        }
        return Product.builder()
                .id(dto.getId())
                .sku(dto.getSku())
                .name(dto.getName())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .quantity(dto.getQuantity())
                .priceEuro(dto.getPriceEuro())
                .productCategory(productCategoryRepository.findFirstByName(dto.getCategory()))
                .productDiscount(productDiscount)
                .build();
    }

    @Override
    protected ProductDto convertToDto(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .name(entity.getName())
                .description(entity.getDescription())
                .imageUrl(entity.getImageUrl())
                .priceEuro(entity.getPriceEuro())
                .quantity(entity.getQuantity())
                .category(entity.getProductCategory().getName())
                .discount(entity.getProductDiscount().getName())
                .build();
    }
}