package com.blogspot.mvnblogbuild.telemetry.kafkatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Check this https://habr.com/ru/post/440400/
 */
@SpringBootApplication
public class KafkaTestApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(KafkaTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(KafkaTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Hello");
    }
}
