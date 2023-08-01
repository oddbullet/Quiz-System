package com.example.application.data.entity;


import jakarta.persistence.*;

@Entity
@Table
public class Question {

    @Id
    @SequenceGenerator(name = "question_seq", sequenceName = "QUESTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    private Long id;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private String correctText;

    @Column(nullable = false)
    private String wrongText1;

    @Column(nullable = false)
    private String wrongText2;

    @Column(nullable = false)
    private String wrongText3;

    public Question() {

    }

    public Question(String questionText, String correctText, String wrongText1, String wrongText2, String wrongText3) {
        this.questionText = questionText;
        this.correctText = correctText;
        this.wrongText1 = wrongText1;
        this.wrongText2 = wrongText2;
        this.wrongText3 = wrongText3;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectText() {
        return correctText;
    }

    public void setCorrectText(String correctText) {
        this.correctText = correctText;
    }

    public String getWrongText1() {
        return wrongText1;
    }

    public void setWrongText1(String wrongText1) {
        this.wrongText1 = wrongText1;
    }

    public String getWrongText2() {
        return wrongText2;
    }

    public void setWrongText2(String wrongText2) {
        this.wrongText2 = wrongText2;
    }

    public String getWrongText3() {
        return wrongText3;
    }

    public void setWrongText3(String wrongText3) {
        this.wrongText3 = wrongText3;
    }
}
