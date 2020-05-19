package com.quiz.backend.service;

import com.quiz.backend.dao.QuizDao;
import com.quiz.backend.controller.data.object.AnswerOption;
import com.quiz.backend.controller.data.object.Question;
import com.quiz.backend.entity.AnswerOptionBE;
import com.quiz.backend.entity.QuestionBE;
import com.quiz.backend.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    public void createQuestion (Question question) throws InvalidInputException {

        verifyInput(question);
        QuestionBE questionBE = mapQuestion(question);
        quizDao.persistQuestion(questionBE);
    }

    private void verifyInput(Question question) throws InvalidInputException {
        if(question.getLanguage() == 0) {
            throw new InvalidInputException("Language not set");
        }
    }

    private QuestionBE mapQuestion(Question question) {

        QuestionBE questionBE = new QuestionBE();

        List<AnswerOption> options = question.getOptions();
        List<AnswerOptionBE> answerOptionBES = new ArrayList<>();

        questionBE.setLang(question.getLanguage());
        questionBE.setText(question.getQuestionText());
        questionBE.setSource(question.getSource());
        questionBE.setCategory(question.getCategory());
        questionBE.setExplanation(question.getExplanation());


        for( AnswerOption option : options ) {
            AnswerOptionBE answerOptionBE = new AnswerOptionBE();

            answerOptionBE.setCorrectOption(option.getIsCorrect());
            answerOptionBE.setAnswerText(option.getAnswerText());
            answerOptionBE.setQuestionBE(questionBE);

            answerOptionBES.add(answerOptionBE);
        }
        questionBE.getOptions().addAll(answerOptionBES);
        return questionBE;

    }

    public List<Question> getQuestions( int pageNumber, int entriesPerPage, int langId) {

        List<QuestionBE> questionBEs = quizDao.getQuestions(pageNumber,entriesPerPage, langId);
        List<Question> questions = mapQuestions(questionBEs);
        return questions;
    }

    private List<Question> mapQuestions(List<QuestionBE> questionBEs) {
        List<Question> questions = new ArrayList<>();
        questionBEs.forEach( questionBE -> {
                questions.add(mapQuestionFromBE(questionBE));
                });
        return questions;
    }

    private Question mapQuestionFromBE(QuestionBE questionBE) {
        Question question = new Question();

        question.setId(questionBE.getId());
        question.setCategory(questionBE.getCategory());
        question.setExplanation(questionBE.getExplanation());
        question.setLanguage(questionBE.getLang());
        question.getOptions().addAll(mapOptions(questionBE.getOptions()));
        question.setQuestionText(questionBE.getText());
        question.setSource(questionBE.getSource());

        return question;
    }

    private Collection<? extends AnswerOption> mapOptions(List<AnswerOptionBE> options) {
        List<AnswerOption> answerOptions = new ArrayList<>();
        options.forEach(answerOptionBE -> {
            AnswerOption answerOption = new AnswerOption();

            answerOption.setAnswerText(answerOptionBE.getAnswerText());
            answerOption.setId(answerOptionBE.getId());
            answerOption.setIsCorrect(answerOptionBE.isCorrectOption());

            answerOptions.add(answerOption);
        });

        return answerOptions;
    }

    public Question getRandomQuestion(int langId) {
        QuestionBE randomQuestionBE = quizDao.getRandomQuestion(langId);
        return  mapQuestionFromBE(randomQuestionBE);
    }
}
