package com.quiz.backend.controller;

import com.quiz.backend.controller.data.object.ExcludeList;
import com.quiz.backend.controller.data.object.Question;
import com.quiz.backend.exception.InvalidInputException;
import com.quiz.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz/questions")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/get-random")
    public ResponseEntity<?>  getRandomQuestion(@RequestParam int langId, @RequestBody(required = false) ExcludeList excludeList) {
        ResponseEntity<Question> responseEntity;

        Question question = quizService.getRandomQuestion(langId,excludeList.getExcludeList());
        responseEntity = ResponseEntity.ok().body(question);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<?> getQuestions(@RequestParam int pageNumber, @RequestParam int entriesPerPage,
                                          @RequestParam int langId) {

        ResponseEntity<List<Question>> responseEntity;
        if(pageNumber < 1 || entriesPerPage < 1 || langId < 1) {
            responseEntity = ResponseEntity.badRequest().body(null);
            return responseEntity;
        }

        List<Question> questions = quizService.getQuestions(pageNumber, entriesPerPage, langId);
        responseEntity = ResponseEntity.ok().body(questions);
        return responseEntity;
    }

    @PostMapping
    public String createQuestion(@RequestBody Question question) {

        try {
            quizService.createQuestion(question);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        return "Question Created";
    }

}
