package com.malanukha.market.view.admin;

import com.malanukha.market.domain.shopping.Order;
import com.malanukha.market.domain.shopping.OrderPaymentStatus;
import com.malanukha.market.dto.product.OrderDto;
import com.malanukha.market.service.admin.OrderAdminService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@PageTitle("Orders")
@Route(value = "admin/orders/:entityID?/:action?(edit)", layout = AdminLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class AdminOrdersView extends BaseAdminView<OrderDto, Order> {

    public AdminOrdersView(OrderAdminService orderAdminService, ProductCategoryService productCategoryService, ProductDiscountService productDiscountService) {
        super(orderAdminService, productCategoryService, productDiscountService);
    }

    @Override
    protected Class<OrderDto> getEntityClass() {
        return OrderDto.class;
    }

    @Override
    protected Class<? extends Div> getViewClass() {
        return AdminOrdersView.class;
    }

    @Override
    protected List<String> getGridColumnNames() {
        return List.of("id", "username", "orderTotal", "provider", "amountPayed", "paymentStatus");
    }

    @Override
    protected List<Pair<TextField, Optional<Converter>>> getGridColumnsWithConverters() {
        TextField orderTotal = new TextField("orderTotal");
        //orderTotal.setReadOnly(true);todo
        return List.of(
                Pair.with(new TextField("id"), Optional.of(new StringToLongConverter("must be a long"))),
                Pair.with(new TextField("username"), Optional.empty()),
                Pair.with(orderTotal, Optional.of(new StringToBigDecimalConverter("must be a BigDecimal"))),
                Pair.with(new TextField("provider"), Optional.empty()),
                Pair.with(new TextField("amountPayed"), Optional.of(new StringToBigDecimalConverter("must be a BigDecimal")))
        );
    }

    @Override
    protected List<Select<String>> getSelectColumns() {
        Select<String> paymentStatus = new Select<>();
        paymentStatus.setLabel("paymentStatus");
        paymentStatus.setItems(Arrays.stream(OrderPaymentStatus.values()).map(OrderPaymentStatus::name).toList());
        return List.of(paymentStatus);
    }

    @Override
    protected Long getIdFromEntity(OrderDto sampleEntity) {
        return sampleEntity.getId();
    }

    @Override
    protected String getRoute() {
        return "orders";
    }

    @Override
    protected OrderDto getNewEntityObject() {
        return new OrderDto();
    }
}
