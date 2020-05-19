package com.quiz.backend.dao;

import com.quiz.backend.entity.QuestionBE;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Repository
public class QuizDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void persistQuestion(QuestionBE questionBE) {
        entityManager.persist(questionBE);
    }

    @Transactional
    public List<QuestionBE> getQuestions(int pageNumber, int entriesPerPage, int langId) {
        int firstResult = (pageNumber-1) * entriesPerPage;
        return
            entityManager.createNamedQuery(QuestionBE.GET_QUESTIONS_FOR_LANG, QuestionBE.class)
                    .setParameter(1,Long.valueOf(langId))
                    .setFirstResult(firstResult)
                    .setMaxResults(entriesPerPage)
                    .getResultList();
    }

    @Transactional
    public QuestionBE getRandomQuestion(int langId) {
        List<QuestionBE> questionBES = getQuestions(1,2000000,langId);
        Random generator = new Random();
        int randomIndex = generator.nextInt(questionBES.size()-1);
        if(questionBES.size() < 1) {
            return new QuestionBE();
        }
        return questionBES.get(randomIndex);
    }
}
