package com.malanukha.market.view.admin;

import com.malanukha.market.domain.product.Product;
import com.malanukha.market.dto.ProductDto;
import com.malanukha.market.service.admin.ProductAdminService;
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
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.javatuples.Pair;

import java.util.List;
import java.util.Optional;

@PageTitle("Products")
@Route(value = "admin/products/:entityID?/:action?(edit)", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class AdminProductsView extends BaseAdminView<ProductDto, Product> {

    public AdminProductsView(ProductAdminService productAdminService,
                             ProductCategoryService productCategoryService,
                             ProductDiscountService productDiscountService) {
        super(productAdminService, productCategoryService, productDiscountService);
    }

    @Override
    protected Class<ProductDto> getEntityClass() {
        return ProductDto.class;
    }

    @Override
    protected Class<? extends Div> getViewClass() {
        return AdminProductsView.class;
    }

    @Override
    protected List<String> getGridColumnNames() {
        return List.of("id", "sku", "name", "description", "imageUrl", "quantity", "priceEuro", "category", "discount");
    }

    @Override
    protected List<Pair<TextField, Optional<Converter>>> getGridColumnsWithConverters() {
        return List.of(
                Pair.with(new TextField("id"), Optional.of(new StringToLongConverter("must be a long"))),
                Pair.with(new TextField("sku"), Optional.empty()),
                Pair.with(new TextField("name"), Optional.empty()),
                Pair.with(new TextField("description"), Optional.empty()),
                Pair.with(new TextField("imageUrl"), Optional.empty()),
                Pair.with(new TextField("quantity"), Optional.of(new StringToIntegerConverter("must be an integer"))),
                Pair.with(new TextField("priceEuro"), Optional.of(new StringToBigDecimalConverter("must be a BigDecimal"))));
    }

    @Override
    protected List<Select<String>> getSelectColumns() {
        List<String> categoriesNames = productCategoryService.getProductCategoryNames();
        List<String> discountsNames = productDiscountService.getProductDiscountNames();
        if (categoriesNames.isEmpty()) {
            editorDataIsConfigured = false;
        }

        Select<String> categoriesSelect = new Select<>();
        categoriesSelect.setLabel("category");
        categoriesSelect.setItems(categoriesNames);

        Select<String> discountsSelect = new Select<>();
        discountsSelect.setLabel("discount");
        discountsSelect.setItems(discountsNames);
        discountsSelect.setEmptySelectionAllowed(true);

        return List.of(categoriesSelect, discountsSelect);
    }

    @Override
    protected Long getIdFromEntity(ProductDto sampleEntity) {
        return sampleEntity.getId();
    }

    @Override
    protected String getRoute() {
        return "products";
    }

    @Override
    protected ProductDto getNewEntityObject() {
        return new ProductDto();
    }
}
