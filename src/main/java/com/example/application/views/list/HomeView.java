package com.example.application.views.list;

import com.example.application.data.entity.QuestionSet;
import com.example.application.data.service.QuizSystemService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Collections;

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {
    Grid<QuestionSet> grid = new Grid<>(QuestionSet.class);
    TextField filterText = new TextField();
    QuizForm form;
    QuizSystemService service;

    public HomeView(QuizSystemService service) {
        this.service = service;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());

        updateList();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.setFlexGrow(1, grid);
//        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassName("questionSet-grid");
        grid.setSizeFull();
        grid.setColumns("name", "numberOfQuestion");
        grid.addComponentColumn(quiz -> {
           Button startButton = new Button("Start");
           return startButton;
        });
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureForm() {
        form = new QuizForm(Collections.emptyList());
        form.setWidth("25em");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button newSetButton = new Button("New Set");
        newSetButton.addClickListener(event -> newSetButton.getUI().ifPresent(ui -> ui.navigate("newSet")));

        var toolbar = new HorizontalLayout(filterText, newSetButton);
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.findAllSet());
    }
}
