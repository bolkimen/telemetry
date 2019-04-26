package com.blogspot.mvnblogbuild.telemetry.receiver;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DataReceiverApplication implements DataReaderController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    private int applicationId = (new Random()).nextInt();

    public static void main(String[] args) {
        SpringApplication.run(DataReceiverApplication.class, args);
    }

    public int getApplicationId() {
        return applicationId;
    }

    @Override
    public String greeting() {
        return String.format("Hello from '%s with Port Number %s'!", eurekaClient.getApplication(appName)
            .getName(), applicationId);
    }

    @Override
    public String readData(@Valid @RequestBody DeviceDataDTO data) {
        return String.format("Receive data %s from '%s with Port Number %s'!",
                data,
                eurekaClient.getApplication(appName).getName(),
                applicationId);
    }
}
