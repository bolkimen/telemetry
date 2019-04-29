package com.blogspot.mvnblogbuild.telemetry.receiver.controller;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import com.blogspot.mvnblogbuild.telemetry.receiver.service.DeviceDataService;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;

@RestController
public class DataReaderController implements DataReaderControllerApi {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Autowired
    private DeviceDataService deviceDataService;

    @Value("${spring.application.name}")
    private String appName;

    private int applicationId = (new Random()).nextInt();

    public int getApplicationId() {
        return applicationId;
    }

    @Override
    public String healthCheck() {
        return String.format("Hello from '%s with Port Number %s'!", eurekaClient.getApplication(appName)
                .getName(), applicationId);
    }

    @Override
    public String readData(@Valid @RequestBody DeviceDataDTO data) {
        deviceDataService.publishData(data);

        return String.format("Receive data %s from '%s with Port Number %s'!",
                data,
                eurekaClient.getApplication(appName).getName(),
                applicationId);
    }


}
