package com.example.application.views.list;

import com.example.application.data.entity.Question;
import com.example.application.data.entity.QuestionSet;
import com.example.application.data.service.QuizSystemService;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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
        Button cancel = new Button("Cancel");

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickListener(event -> primeSetForSave(questionContainers));
        cancel.addClickListener(event -> cancel.getUI().ifPresent(ui -> ui.navigate("home")));

        return new HorizontalLayout(addQuestion, save, cancel);
    }


    private void primeSetForSave(List<QuestionContainer> questionContainers) {
        QuestionSet existingSet = service.findSetByName(setName.getValue());
        boolean[] cont = {true};

        //Does set exist
        if(existingSet == null) {
            existingSet = new QuestionSet(setName.getValue(), new HashSet<>(), 0);
            saveSet(existingSet, questionContainers);

        } else {
            QuestionSet[] finalSet = {existingSet};
            ConfirmDialog dialog = new ConfirmDialog();
            dialog.setHeader("Confirmation");
            dialog.setText(new Html("<p>Overwrite current set?</p>"));

            dialog.setCancelable(true);
            dialog.setConfirmText("Continue");

            dialog.addCancelListener(event -> dialog.close());

            dialog.addConfirmListener(event -> {
                dialog.close();
                saveSet(finalSet[0], questionContainers);
            });

            dialog.open();
        }
    }

    private void saveSet(QuestionSet existingSet, List<QuestionContainer> questionContainers) {

        Set<Question> set = existingSet.getQuestions();
        set.clear();

        for (QuestionContainer container : questionContainers) {
            Question question = new Question();
            try {
                container.getBinder().writeBean(question);
                set.add(question);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
        existingSet.setNumberOfQuestion(set.size());
        service.saveSet(existingSet);
    }
}