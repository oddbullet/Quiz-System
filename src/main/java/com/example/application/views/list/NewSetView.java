package com.example.application.views.list;

import com.example.application.data.entity.Question;
import com.example.application.data.entity.QuestionSet;
import com.example.application.data.service.QuizSystemService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(value = "newSet")
public class NewSetView extends VerticalLayout{
    TextField setName = new TextField("Name");
    List<QuestionContainer> questionContainers = new ArrayList<>();
    HorizontalLayout buttonsLayout = createButtonLayOut();
    QuizSystemService service;

    @Autowired
    public NewSetView(QuizSystemService service) {
        addClassName("new-set-view");

        this.service = service;

        setName.setWidthFull();
        add(setName, buttonsLayout);
    }

    private HorizontalLayout createButtonLayOut() {
        Button addQuestion = new Button("New");
        addQuestion.addClickListener(event -> {
            remove(buttonsLayout);
            QuestionContainer q = new QuestionContainer(this, questionContainers);
            questionContainers.add(q);
            add(q, buttonsLayout);
        });

        Button save = new Button("Save");
        Button delete = new Button("Delete");
        Button cancel = new Button("Cancel");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(event -> saveSet(questionContainers));

        return new HorizontalLayout(addQuestion, save, delete, cancel);
    }

    private void saveSet(List<QuestionContainer> questionContainers) {
        Set<Question> set = new HashSet<>();
        System.out.println(setName.getValue());
        for (QuestionContainer container : questionContainers) {
            Question question = new Question();
            try {
                container.getBinder().writeBean(question);
                set.add(question);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
            System.out.println(question.getQuestionText());
        }
        System.out.println();
        QuestionSet questionSet = new QuestionSet(setName.getValue(), set, set.size());
        service.saveSet(questionSet);
    }
}
