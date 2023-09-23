package com.malanukha.market.view.admin;

import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.dto.product.ProductCategoryDto;
import com.malanukha.market.service.admin.BaseAdminService;
import com.malanukha.market.service.product.ProductCategoryService;
import com.malanukha.market.service.product.ProductDiscountService;
import com.malanukha.market.view.AdminLayout;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.javatuples.Pair;

import java.util.List;
import java.util.Optional;

@PageTitle("Categories")
@Route(value = "admin/categories/:sampleEntityID?/:action?(edit)", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class AdminProductCategoriesView extends BaseAdminView<ProductCategoryDto, ProductCategory> {
    public AdminProductCategoriesView(BaseAdminService<ProductCategoryDto, ProductCategory> baseAdminService, ProductCategoryService productCategoryService, ProductDiscountService productDiscountService) {
        super(baseAdminService, productCategoryService, productDiscountService);
    }

    @Override
    protected Class<ProductCategoryDto> getEntityClass() {
        return ProductCategoryDto.class;
    }

    @Override
    protected Class<? extends Div> getViewClass() {
        return AdminProductCategoriesView.class;
    }

    @Override
    protected List<String> getGridColumnNames() {
        return List.of("id", "name", "description", "isMainCategory", "mainCategoryName");
    }

    @Override
    protected List<Pair<TextField, Optional<Converter>>> getGridColumnsWithConverters() {
        return List.of(
                Pair.with(new TextField("id"), Optional.of(new StringToLongConverter("must be a long"))),
                Pair.with(new TextField("name"), Optional.empty()),
                Pair.with(new TextField("description"), Optional.empty())
        );
    }

    @Override
    protected List<Select<String>> getSelectColumns() {
        Select<String> mainCategoryName = new Select<>();
        mainCategoryName.setLabel("mainCategoryName");
        var mainCategories = productCategoryService.getMainProductCategories().stream()
                .map(ProductCategory::getName)
                .toList(); //todo optimize
        mainCategoryName.setItems(mainCategories);
        mainCategoryName.setVisible(false);

        Select<String> isMainCategory = new Select<>("isMainCategory", event -> {
            mainCategoryName.setVisible(!event.getValue().equals("Yes")); //if it's the main category, it doesn't require an additional field
        }, "Yes", "No");

        return List.of(isMainCategory, mainCategoryName);
    }

    @Override
    protected Long getIdFromEntity(ProductCategoryDto sampleEntity) {
        return sampleEntity.getId();
    }

    @Override
    protected String getRoute() {
        return "categories";
    }

    @Override
    protected ProductCategoryDto getNewEntityObject() {
        return new ProductCategoryDto();
    }
}
