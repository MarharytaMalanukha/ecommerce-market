package com.malanukha.market.view.category;

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
import com.vaadin.flow.theme.lumo.LumoUtility.*;

@PageTitle("Category")
@Route(value = "category/:mainCategoryName", layout = MainLayout.class)
@AnonymousAllowed
public class CategoryView extends Main implements BeforeEnterObserver, HasComponents, HasStyle {

    private static final String MAIN_CATEGORY_NAME = "mainCategoryName";

    private ProductCategory mainCategory;
    private OrderedList imageContainer;

    private final UtilsService utilsService;

    public CategoryView(UtilsService utilsService) {
        this.utilsService = utilsService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String categoryName = event.getRouteParameters()
                .get(MAIN_CATEGORY_NAME)
                .orElseThrow()
                .replace("_", " ");
        mainCategory = utilsService.findCategoryByNameWithCategories(categoryName);

        constructUI();
        for (ProductCategory category : mainCategory.getProductCategories()) {
            imageContainer.add(new CategoryViewCard(category));
        }
    }

    private void constructUI() {
        addClassNames("category-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2(mainCategory.getName());
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph(mainCategory.getDescription());
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        Select<String> sortBy = new Select<>();
        sortBy.setLabel("Sort by");
        sortBy.setItems("Popularity", "Newest first", "Oldest first");
        sortBy.setValue("Popularity");

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer, sortBy);
        add(container, imageContainer);
    }
}