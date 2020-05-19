package com.quiz.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer_options")
public class AnswerOptionBE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Basic
    String answerText;

    @Basic
    boolean isCorrectOption;

    @ManyToOne
    @JoinColumns( {
            @JoinColumn(name = "question_id")
    })
    QuestionBE questionBE;

    public long getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answer_text) {
        this.answerText = answer_text;
    }

    public boolean isCorrectOption() {
        return isCorrectOption;
    }

    public void setCorrectOption(boolean correctOption) {
        isCorrectOption = correctOption;
    }

    public QuestionBE getQuestionBE() {
        return questionBE;
    }

    public void setQuestionBE(QuestionBE questionBE) {
        this.questionBE = questionBE;
    }
}
