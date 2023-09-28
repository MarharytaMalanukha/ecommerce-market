package com.malanukha.market.view;


import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.security.AuthenticatedUser;
import com.malanukha.market.service.utils.UtilsService;
import com.malanukha.market.view.admin.AdminProductsView;
import com.malanukha.market.view.category.CategoryView;
import com.malanukha.market.view.dashboard.DashboardView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import org.javatuples.Pair;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.ArrayList;
import java.util.List;

public class MainLayout extends BaseApplicationLayout {

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, UtilsService utilsService) {
        super(authenticatedUser, accessChecker, utilsService);
    }

    @Override
    protected List<Pair<String, ComponentEventListener<ClickEvent<MenuItem>>>> createProfileMenuItems() {
        return List.of(
                Pair.with("Profile", e -> UI.getCurrent().navigate(DashboardView.class)),
                Pair.with("Admin Panel", e -> UI.getCurrent().navigate(AdminProductsView.class)),
                Pair.with("Sign out",  e -> authenticatedUser.logout()));
    }

    @Override
    protected List<MenuItemInfo> createMenuItems() {
        List<ProductCategory> categories = utilsService.getMainProductCategories();
        List<MenuItemInfo> menuItems = new ArrayList<>();
        menuItems.add(new MenuItemInfo("Dashboard", LineAwesomeIcon.TH_LIST_SOLID.create(), DashboardView.class));
        menuItems.addAll(categories.stream()
                .map(category -> new MenuItemInfo(category.getName(), LineAwesomeIcon.TH_LIST_SOLID.create(),
                        CategoryView.class, new RouteParameters("mainCategoryName", category.getName().replace(" ", "_"))))
                .toList());
        return menuItems;
    }
}