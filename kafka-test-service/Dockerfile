FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/kafka-test-service
ARG JAR_FILE

ENV _JAVA_OPTIONS "-Xms256m -Xmx512m -Djava.awt.headless=true"

COPY ${JAR_FILE} kafka-test-service.jar

RUN addgroup bootapp && \
    adduser -D -S -h /var/cache/bootapp -s /sbin/nologin -G bootapp bootapp

USER bootapp
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "./kafka-test-service.jar"]
