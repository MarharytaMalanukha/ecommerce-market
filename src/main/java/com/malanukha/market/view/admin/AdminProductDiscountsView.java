package com.malanukha.market.view.admin;

import com.malanukha.market.domain.product.ProductDiscount;
import com.malanukha.market.dto.product.ProductDiscountDto;
import com.malanukha.market.service.admin.ProductDiscountAdminService;
import com.malanukha.market.service.product.ProductCategoryService;
import com.malanukha.market.service.product.ProductDiscountService;
import com.malanukha.market.view.AdminLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.javatuples.Pair;

import java.util.List;
import java.util.Optional;

@PageTitle("Discounts")
@Route(value = "admin/discounts/:entityID?/:action?(edit)", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class AdminProductDiscountsView extends BaseAdminView<ProductDiscountDto, ProductDiscount>{
    public AdminProductDiscountsView(ProductDiscountAdminService baseAdminService,
                                     ProductCategoryService productCategoryService,
                                     ProductDiscountService productDiscountService) {
        super(baseAdminService, productCategoryService, productDiscountService);
    }

    @Override
    protected Class<ProductDiscountDto> getEntityClass() {
        return ProductDiscountDto.class;
    }

    @Override
    protected Class<? extends Div> getViewClass() {
        return AdminProductDiscountsView.class;
    }

    @Override
    protected List<String> getGridColumnNames() {
        return List.of("id", "name", "description", "discountPercent", "active");
    }

    @Override
    protected List<Pair<TextField, Optional<Converter>>> getGridColumnsWithConverters() {
        return List.of(
                Pair.with(new TextField("id"), Optional.of(new StringToLongConverter("must be a long"))),
                Pair.with(new TextField("name"), Optional.empty()),
                Pair.with(new TextField("description"), Optional.empty()),
                Pair.with(new TextField("discountPercent"), Optional.of(new StringToBigDecimalConverter("must be a BigDecimal")))
        );
    }

    @Override
    protected List<Select<String>> getSelectColumns() {
        Select<String> active = new Select<>();
        active.setLabel("active");
        active.setItems("Yes", "No");
        return List.of(active);
    }

    @Override
    protected Long getIdFromEntity(ProductDiscountDto sampleEntity) {
        return sampleEntity.getId();
    }

    @Override
    protected String getRoute() {
        return "discounts";
    }

    @Override
    protected ProductDiscountDto getNewEntityObject() {
        return new ProductDiscountDto();
    }
}
