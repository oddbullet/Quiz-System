package com.example.application.views.list;

import com.example.application.data.entity.Question;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class QuestionContainer extends FormLayout {
    private final VerticalLayout parentLayout;
    private final Binder<Question> binder;
    private final List<QuestionContainer> questionContainers;

    public QuestionContainer(VerticalLayout parentLayout, List<QuestionContainer> questionContainers) {
        this.parentLayout = parentLayout;
        this.binder = new Binder<>(Question.class);
        this.questionContainers = questionContainers;


        TextField question = new TextField("Question");
        TextField correctAns = new TextField("Correct Answer");
        TextField wrongAns1 = new TextField("Wrong answer 1");
        TextField wrongAns2 = new TextField("Wrong answer 2");
        TextField wrongAns3 = new TextField("Wrong answer 3");

        binder.bind(question, Question::getQuestionText, Question::setQuestionText);
        binder.bind(correctAns, Question::getCorrectText, Question::setCorrectText);
        binder.bind(wrongAns1, Question::getWrongText1, Question::setWrongText1);
        binder.bind(wrongAns2, Question::getWrongText2, Question::setWrongText2);
        binder.bind(wrongAns3, Question::getWrongText3, Question::setWrongText3);

        question.setWidthFull();
        correctAns.setWidthFull();
        wrongAns1.setWidthFull();
        wrongAns2.setWidthFull();
        wrongAns3.setWidthFull();

        Button remove = new Button("Remove");
        remove.addClickListener(event -> removeQuestionContainer(this));
        remove.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
        add(question, correctAns, wrongAns1, wrongAns2, wrongAns3, remove);
    }

    private void removeQuestionContainer(QuestionContainer container) {
        parentLayout.remove(this);
        questionContainers.remove(this);
    }

    public Binder<Question> getBinder() {
        return binder;
    }
}
