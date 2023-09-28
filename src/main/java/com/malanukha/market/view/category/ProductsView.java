package com.malanukha.market.view.category;

import com.malanukha.market.domain.product.Product;
import com.malanukha.market.domain.product.ProductCategory;
import com.malanukha.market.service.utils.UtilsService;
import com.malanukha.market.view.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Products")
@Route(value = "category/:mainCategoryName/:categoryName/products", layout = MainLayout.class)
@AnonymousAllowed
public class ProductsView extends Main implements BeforeEnterObserver, HasComponents, HasStyle {

    private static final String CATEGORY_NAME = "categoryName";

    private ProductCategory category;
    private OrderedList imageContainer;

    private final UtilsService utilsService;

    public ProductsView(UtilsService utilsService) {
        this.utilsService = utilsService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String categoryName = event.getRouteParameters()
                .get(CATEGORY_NAME)
                .orElseThrow()
                .replace("_", " ");
        category = utilsService.findCategoryByNameWithProducts(categoryName);

        constructUI();
        for (Product product : category.getProducts()) {
            imageContainer.add(new ProductViewCard(product));
        }
    }

    private void constructUI() {
        addClassNames("category-view");
        addClassNames(LumoUtility.MaxWidth.SCREEN_LARGE, LumoUtility.Margin.Horizontal.AUTO, LumoUtility.Padding.Bottom.LARGE, LumoUtility.Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(LumoUtility.AlignItems.CENTER, LumoUtility.JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2(category.getName());
        header.addClassNames(LumoUtility.Margin.Bottom.NONE, LumoUtility.Margin.Top.XLARGE, LumoUtility.FontSize.XXXLARGE);
        Paragraph description = new Paragraph(category.getDescription());
        description.addClassNames(LumoUtility.Margin.Bottom.XLARGE, LumoUtility.Margin.Top.NONE, LumoUtility.TextColor.SECONDARY);
        headerContainer.add(header, description);

        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");

        imageContainer = new OrderedList();
        imageContainer.addClassNames(LumoUtility.Gap.MEDIUM, LumoUtility.Display.GRID, LumoUtility.ListStyleType.NONE, LumoUtility.Margin.NONE, LumoUtility.Padding.NONE);

        container.add(headerContainer, sortBy);
        add(container, imageContainer);
    }
}
