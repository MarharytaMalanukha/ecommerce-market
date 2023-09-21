package com.malanukha.market.view;

import com.malanukha.market.security.AuthenticatedUser;
import com.malanukha.market.view.layout.BaseApplicationLayout;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;

import java.util.Map;

public class AdminLayout extends BaseApplicationLayout {

    public AdminLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        super(authenticatedUser, accessChecker);
    }

    @Override
    protected Map<String, ComponentEventListener<ClickEvent<MenuItem>>> createProfileMenuItems() {
        return Map.of("Sign out",  e -> authenticatedUser.logout());
    }

    protected MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{

                //new MenuItemInfo("Users", LineAwesomeIcon.FILTER_SOLID.create(), UsersView.class), //

                /*new MenuItemInfo("Categories", LineAwesomeIcon.COLUMNS_SOLID.create(), CategoriesView.class), //
                new MenuItemInfo("Products", LineAwesomeIcon.COLUMNS_SOLID.create(), ProductsView.class), //
                new MenuItemInfo("Discounts", LineAwesomeIcon.COLUMNS_SOLID.create(), DiscountsView.class), //
                new MenuItemInfo("Orders", LineAwesomeIcon.COLUMNS_SOLID.create(), OrdersView.class), //
                new MenuItemInfo("SuperAdmin panel", LineAwesomeIcon.FILE.create(), SuperAdminpanelView.class), //*/

        };
    }

}
