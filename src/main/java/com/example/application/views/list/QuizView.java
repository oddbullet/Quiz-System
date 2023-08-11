package com.example.application.views.list;

import com.example.application.data.entity.Question;
import com.example.application.data.entity.QuestionSet;
import com.example.application.data.service.QuizSystemService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Route("quiz")
public class QuizView extends VerticalLayout implements HasUrlParameter<String> {

    private final QuizSystemService service;
    private Optional<QuestionSet> optionalQuestionSet;


    @Autowired
    public QuizView(QuizSystemService service) {
        this.service = service;
    }
    

    @Override
    @Transactional
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null) {
            Long questionSetId = Long.parseLong(parameter);
            optionalQuestionSet = service.findSetById(questionSetId);
            if(optionalQuestionSet.isPresent()) {
                QuestionSet s = optionalQuestionSet.get();
                printSetName(s.getName());
                Set<Question> x = s.getQuestions();
                System.out.println("Size: " + x.size());
                for(Question question : x) {
                    String name = question.getQuestionText();
                    System.out.println("Quiz: " + name);
                }
            }



//            System.out.println("Size: " + optionalQuestionSet.get().getQuestions().size());
//
//            for(Question question : optionalQuestionSet.get().getQuestions()) {
//                System.out.println("Quiz: " + question.getQuestionText());
//            }
//
//            optionalQuestionSet.ifPresent(questionSet -> {
//                printSetName(questionSet.getName());
//                System.out.println("Load + " + questionSet.getQuestions().size());
//            });

        }
    }

    public void printSetName(String setName) {
        H2 name = new H2(setName);
        add(name);
    }

    public void createQuestions(Set<Question> questions) {
        System.out.println("ajfaewnf;");
        System.out.println(questions.size());
        Div container = new Div();
        for(Question question : questions) {
            RadioButtonGroup<String> radioButton = new RadioButtonGroup<>();
            radioButton.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
            radioButton.setLabel("");
            radioButton.setItems(question.getCorrectText(), question.getWrongText1(), question.getWrongText2(), question.getWrongText3());
            radioButton.addValueChangeListener(event -> {
                String x = event.getValue();
                System.out.println(x);
            });
            System.out.println(question.getQuestionText());
            container.add(new H4(question.getQuestionText()), radioButton);
        }
        add(container);
    }
}
