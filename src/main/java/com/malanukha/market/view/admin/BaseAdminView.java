package com.malanukha.market.view.admin;

import com.malanukha.market.service.admin.BaseAdminService;
import com.malanukha.market.service.utils.UtilsService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.javatuples.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;
import java.util.Optional;

public abstract class BaseAdminView<T, T2> extends Div implements BeforeEnterObserver {
    private static final String ENTITY_ID = "entityID";
    private static final String ENTITY_EDIT_ROUTE_TEMPLATE = "admin/%s/%s/edit";

    private final Grid<T> grid;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");
    private final Button delete = new Button("Delete");

    private Binder<T> binder;

    private T sampleEntity;

    protected final BaseAdminService<T, T2> baseAdminService;
    protected final UtilsService utilsService;

    private final List<Pair<TextField, Optional<Converter>>> listOfGridColumns;
    private final List<Select<String>> listOfSelectColumns;
    protected boolean editorDataIsConfigured = true;

    public BaseAdminView(BaseAdminService<T, T2> baseAdminService, UtilsService utilsService) {
        this.baseAdminService = baseAdminService;
        this.utilsService = utilsService;

        listOfGridColumns = getGridColumnsWithConverters();
        listOfSelectColumns = getSelectColumns();

        grid = new Grid<>(getEntityClass(), false);
        addClassNames(getRoute() + "-view");
        setHeightFull();

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setHeightFull();

        createGridLayout(splitLayout);
        if (editorDataIsConfigured) {
            createEditorLayout(splitLayout);
            splitLayout.setSplitterPosition(60);
        } else {
            splitLayout.setSplitterPosition(100);
        }

        add(splitLayout);

        for (String columnName : getGridColumnNames()) {
            grid.addColumn(columnName).setAutoWidth(true);
        }

        grid.setItems(query -> baseAdminService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(ENTITY_EDIT_ROUTE_TEMPLATE, getRoute(), getIdFromEntity(event.getValue())));
            } else {
                clearForm();
                UI.getCurrent().navigate(getViewClass());
            }
        });

        if (editorDataIsConfigured){
            configureEditorLayout();
        }
    }

    protected abstract Class<T> getEntityClass();
    protected abstract Class<? extends Div> getViewClass();
    protected abstract List<String> getGridColumnNames();
    protected abstract List<Pair<TextField, Optional<Converter>>> getGridColumnsWithConverters();
    protected abstract List<Select<String>> getSelectColumns();
    protected abstract Long getIdFromEntity(T sampleEntity);
    protected abstract String getRoute();
    protected abstract T getNewEntityObject();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> entityId = event.getRouteParameters().get(ENTITY_ID).map(Long::parseLong);
        if (entityId.isPresent()) {
            Optional<T> samplePersonFromBackend = baseAdminService.get(entityId.get());
            if (samplePersonFromBackend.isPresent()) {
                populateForm(samplePersonFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested entity was not found, ID = %d", entityId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                refreshGrid();
                event.forwardTo(getViewClass());
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();

        for (Pair<TextField, Optional<Converter>> pair : listOfGridColumns) {
            formLayout.add(pair.getValue0());
        }
        for (Select<String> select : listOfSelectColumns) {
            formLayout.add(select);
        }

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel, delete);
        editorLayoutDiv.add(buttonLayout);
    }

    private void configureEditorLayout() {
        binder = new Binder<>(getEntityClass());

        listOfGridColumns.forEach(pair -> {
            TextField field = pair.getValue0();
            Optional<Converter> converter = pair.getValue1();
            if (converter.isEmpty()) {
                binder.forField(field).bind(field.getLabel());
            } else {
                binder.forField(field).withConverter(converter.get()).bind(field.getLabel());
            }
        });
        listOfSelectColumns.forEach(stringSelect -> binder.forField(stringSelect).bind(stringSelect.getLabel()));

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.sampleEntity == null) {
                    this.sampleEntity = getNewEntityObject();
                }
                binder.writeBean(this.sampleEntity);
                baseAdminService.update(this.sampleEntity);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(getViewClass());
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Notification.Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });

        delete.addClickListener(e -> {
            try {
                baseAdminService.delete(getIdFromEntity(this.sampleEntity));
            } catch (Exception exception) {
                Notification.show("Cannot delete the item");
            }
        });
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(T value) {
        this.sampleEntity = value;
        binder.readBean(this.sampleEntity);
    }
}
