package com.quiz.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.quiz.backend")
public class QuizBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizBackendApplication.class, args);
    }

}
