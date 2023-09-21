package com.malanukha.market.view.category;

import com.malanukha.market.view.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Category")
@Route(value = "category", layout = MainLayout.class)
public class CategoryView extends Composite<VerticalLayout> {

    public CategoryView() {
        getContent().setHeightFull();
        getContent().setWidthFull();
    }
}
