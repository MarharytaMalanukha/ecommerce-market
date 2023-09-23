package com.malanukha.market.view;


import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.security.AuthenticatedUser;
import com.malanukha.market.service.product.ProductCategoryService;
import com.malanukha.market.view.admin.AdminProductsView;
import com.malanukha.market.view.category.CategoryView;
import com.malanukha.market.view.error.EmptyView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;
import java.util.Map;

public class MainLayout extends BaseApplicationLayout {

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, ProductCategoryService productCategoryService) {
        super(authenticatedUser, accessChecker, productCategoryService);
    }

    @Override
    protected Map<String, ComponentEventListener<ClickEvent<MenuItem>>> createProfileMenuItems() {
        return Map.of("Profile", e -> UI.getCurrent().navigate(EmptyView.class),
                "Admin Panel", e -> UI.getCurrent().navigate(AdminProductsView.class),
                "Sign out",  e -> authenticatedUser.logout());
    }

    @Override
    protected MenuItemInfo[] createMenuItems() {
        List<ProductCategory> categories = productCategoryService.getMainProductCategories();
        if (categories.isEmpty())
            return new MenuItemInfo[] { new MenuItemInfo("Empty View", LineAwesomeIcon.TH_LIST_SOLID.create(), EmptyView.class) };
        return categories.stream()
                .map(category -> new MenuItemInfo(category.getName(),
                        LineAwesomeIcon.TH_LIST_SOLID.create(), CategoryView.class))
                .toArray(MenuItemInfo[]::new);
    }
}