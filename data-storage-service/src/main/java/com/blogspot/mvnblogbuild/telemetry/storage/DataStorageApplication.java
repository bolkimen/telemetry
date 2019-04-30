package com.blogspot.mvnblogbuild.telemetry.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataStorageApplication.class, args);
    }

}
