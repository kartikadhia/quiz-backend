package com.quiz.backend.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@NamedQuery(name = QuestionBE.GET_QUESTIONS , query = QuestionBE.GET_QUESTIONS_QUERY)
@NamedQuery(name = QuestionBE.GET_QUESTIONS_FOR_LANG , query = QuestionBE.GET_QUESTIONS_FOR_LANG_QUERY)
@NamedQuery(name = QuestionBE.GET_QUESTION_FOR_ID , query = QuestionBE.GET_QUESTION_FOR_ID_QUERY)
public class QuestionBE implements Serializable {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany(mappedBy = "questionBE", cascade = CascadeType.ALL)
    List<AnswerOptionBE> options = new ArrayList<>();

    long lang;

    @Lob
    String text;

    @Lob
    String explanation;

    @Lob
    String source;

    String category;

    public final static String GET_QUESTIONS = "GET_QUESTIONS_QUERY";

    public final static String GET_QUESTIONS_FOR_LANG = "GET_QUESTIONS_FOR_LANG_QUERY";

    public final static String GET_QUESTION_FOR_ID = "GET_QUESTIONS_FOR_ID_QUERY";

    protected final static String GET_QUESTIONS_QUERY = "select q from QuestionBE q order by question_id asc ";

    protected final static String GET_QUESTIONS_FOR_LANG_QUERY = "select q from QuestionBE q where q.lang = ?1 order by question_id asc ";

    protected final static String GET_QUESTION_FOR_ID_QUERY = "select q from QuestionBE q where q.lang = ?1 and question_id = ?2 ";

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public long getLang() {
        return lang;
    }

    public void setLang(long lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AnswerOptionBE> getOptions() {
        return options;
    }

    public void setOptions(List<AnswerOptionBE> options) {
        this.options = options;
    }
}
