package com.example.application.data.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class QuestionSet {

    @Id
    @SequenceGenerator(name = "question_set_seq", sequenceName = "QUESTION_SET_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_set_seq")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_set_id")
    private Set<Question> questions = new HashSet<>();

    private int numberOfQuestion;

    public QuestionSet() {

    }

    public QuestionSet(String name) {
        this.name = name;
    }

    public QuestionSet(String name, Set<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public QuestionSet(String name, Set<Question> questions, int numberOfQuestion) {
        this.name = name;
        this.questions = questions;
        this.numberOfQuestion = numberOfQuestion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }
}

