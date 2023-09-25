package com.malanukha.market.view;

import com.malanukha.market.security.AuthenticatedUser;
import com.malanukha.market.service.utils.UtilsService;
import com.malanukha.market.view.admin.*;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Map;

public class AdminLayout extends BaseApplicationLayout {

    public AdminLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, UtilsService utilsService) {
        super(authenticatedUser, accessChecker, utilsService);
    }

    @Override
    protected Map<String, ComponentEventListener<ClickEvent<MenuItem>>> createProfileMenuItems() {
        return Map.of("Sign out",  e -> authenticatedUser.logout());
    }

    protected MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{
                //dashboard
                new MenuItemInfo("Find Users", LineAwesomeIcon.FILTER_SOLID.create(), AdminUsersFilterView.class), //
                new MenuItemInfo("Product Categories", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminProductCategoriesView.class),
                new MenuItemInfo("Products", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminProductsView.class),
                new MenuItemInfo("Discounts", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminProductDiscountsView.class),
                new MenuItemInfo("Orders", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminOrdersView.class),
                new MenuItemInfo("Users", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminUsersView.class),
              //  new MenuItemInfo("Order Items", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminOrderItemsFilterView.class)
              //  new MenuItemInfo("SuperAdmin panel", LineAwesomeIcon.FILE.create(), SuperAdminpanelView.class),

        };
    }

}
