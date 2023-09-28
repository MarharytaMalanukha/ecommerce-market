package com.malanukha.market.view.category;

import com.malanukha.market.domain.product.ProductCategory;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.util.Map;

public class CategoryViewCard extends ListItem {

    public CategoryViewCard(ProductCategory category) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE);

        Div div = new Div();
        div.addClassNames(Background.CONTRAST, Display.FLEX, AlignItems.CENTER, JustifyContent.CENTER,
                Margin.Bottom.MEDIUM, Overflow.HIDDEN, BorderRadius.MEDIUM, Width.FULL);
        div.setHeight("160px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc("https://images.unsplash.com/photo-1519681393784-d120267933ba?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80");
        image.setAlt("Snow mountains under stars");

        div.add(image);

        Span header = new Span();
        header.addClassNames(FontSize.XLARGE, FontWeight.SEMIBOLD);
        header.setText(category.getName());

        Span subtitle = new Span();
        subtitle.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subtitle.setText(category.getDescription());

        Paragraph description = new Paragraph(
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.");
        description.addClassName(Margin.Vertical.MEDIUM);

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText("Label");

        RouterLink link = new RouterLink();
        link.setRoute(ProductsView.class,
                new RouteParameters(
                        Map.of(
                        "mainCategoryName", formRouteParameter(category.getMainProductCategory().getName()),
                        "categoryName", formRouteParameter(category.getName())
                        )
                )
        );

        link.add(div, header, subtitle, description, badge);
        add(link);
    }

    private String formRouteParameter(String rawParameter) {
        return rawParameter.replace(" ", "_");
    }
}
