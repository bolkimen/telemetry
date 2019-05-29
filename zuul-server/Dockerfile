FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/zuul

COPY ./target/*.jar zuul.jar
ENTRYPOINT ["java", "-jar", "./zuul.jar"]