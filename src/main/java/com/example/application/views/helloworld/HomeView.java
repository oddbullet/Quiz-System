package com.example.application.views.helloworld;

import com.example.application.data.QuestionSet;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    Grid<QuestionSet> grid = new Grid<>(QuestionSet.class);
    TextField filterText = new TextField();

    public HomeView() {
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassName("questionSet-grid");
        grid.setSizeFull();
        grid.setColumns("name", "numberOfQuestion");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button newSetButton = new Button("New Set");
        var toolbar = new HorizontalLayout(filterText, newSetButton);
        return toolbar;
    }
}
