FROM openjdk:8
EXPOSE 8080
ADD target/quiz-backend.jar quiz-backend.jar
ENTRYPOINT ["java","-jar","/quiz-backend.jar"]
