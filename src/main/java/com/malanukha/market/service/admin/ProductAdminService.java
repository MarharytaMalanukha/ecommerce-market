package com.malanukha.market.service.admin;

import com.malanukha.market.domain.product.Product;
import com.malanukha.market.dto.product.ProductDto;
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
        return Product.builder()
                .id(dto.getId())
                .sku(dto.getSku())
                .name(dto.getName())
                .description(dto.getDescription())
                .imageUrl(dto.getImageUrl())
                .quantity(dto.getQuantity())
                .priceEuro(dto.getPriceEuro())
                .productCategory(productCategoryRepository.findFirstByName(dto.getCategory()))
                .productDiscount(productDiscountRepository.findFirstByName(dto.getDiscount()))
                .build();
    }

    @Override
    protected ProductDto convertToDto(Product entity) {
        return new ProductDto(entity);
    }
}