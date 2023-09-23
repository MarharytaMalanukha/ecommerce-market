package com.malanukha.market.view;

import com.malanukha.market.security.AuthenticatedUser;
import com.malanukha.market.service.product.ProductCategoryService;
import com.malanukha.market.view.admin.AdminProductsView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Map;

public class AdminLayout extends BaseApplicationLayout {

    public AdminLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker, ProductCategoryService productCategoryService) {
        super(authenticatedUser, accessChecker, productCategoryService);
    }

    @Override
    protected Map<String, ComponentEventListener<ClickEvent<MenuItem>>> createProfileMenuItems() {
        return Map.of("Sign out",  e -> authenticatedUser.logout());
    }

    protected MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{

                //new MenuItemInfo("Users", LineAwesomeIcon.FILTER_SOLID.create(), UsersView.class), //

               // new MenuItemInfo("Categories", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminCategoriesView.class),
                new MenuItemInfo("Products", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminProductsView.class),
                //new MenuItemInfo("Discounts", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminDiscountsView.class),
                //new MenuItemInfo("Orders", LineAwesomeIcon.COLUMNS_SOLID.create(), AdminOrdersView.class),
              //  new MenuItemInfo("SuperAdmin panel", LineAwesomeIcon.FILE.create(), SuperAdminpanelView.class),

        };
    }

}
