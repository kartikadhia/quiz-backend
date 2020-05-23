package com.quiz.backend.dao;

import com.quiz.backend.entity.QuestionBE;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public QuestionBE getRandomQuestion(int langId, List<Integer> excludeList) {
        List<QuestionBE> questionBES = getQuestions(1,2000000,langId);
        List<QuestionBE> questionBECopy = new ArrayList<>(questionBES);

        if(excludeList != null && excludeList.size() > 0) {
            for(QuestionBE questionBE: questionBECopy ) {
                if(excludeList.contains(Integer.valueOf((int) questionBE.getId()))) {
                    questionBES.remove(questionBE);
                }
            }
        }

        Random generator = new Random();
        int randomIndex = generator.nextInt(questionBES.size());
        return questionBES.get(randomIndex);
    }
}
