package com.malanukha.market.view;

import com.malanukha.market.security.AuthenticatedUser;
import com.malanukha.market.service.utils.UtilsService;
import com.malanukha.market.view.admin.*;
import com.malanukha.market.view.dashboard.DashboardView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import org.javatuples.Pair;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.List;

public class AdminLayout extends BaseApplicationLayout {

    public AdminLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, UtilsService utilsService) {
        super(authenticatedUser, accessChecker, utilsService);
    }

    @Override
    protected List<Pair<String, ComponentEventListener<ClickEvent<MenuItem>>>> createProfileMenuItems() {
        return List.of(
                Pair.with("Back to Website", e -> UI.getCurrent().navigate(DashboardView.class)),
                Pair.with("Sign out", e -> authenticatedUser.logout())
        );
    }

    protected List<MenuItemInfo> createMenuItems() {
        return List.of(
                //dashboard
                new MenuItemInfo("Find Users", LineAwesomeIcon.FILTER_SOLID.create(), AdminUsersFilterView.class), //
                new MenuItemInfo("Product Categories", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminProductCategoriesView.class),
                new MenuItemInfo("Products", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminProductsView.class),
                new MenuItemInfo("Discounts", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminProductDiscountsView.class),
                new MenuItemInfo("Orders", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminOrdersView.class),
                new MenuItemInfo("Users", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminUsersView.class)
              //  new MenuItemInfo("Order Items", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminOrderItemsFilterView.class)
              //  new MenuItemInfo("SuperAdmin panel", LineAwesomeIcon.FILE.create(), SuperAdminpanelView.class),

        );
    }

}
