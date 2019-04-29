package com.blogspot.mvnblogbuild.telemetry.receiver.service;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataService {

    private static final String TOPIC = "Telemetry";

    @Autowired
    private KafkaTemplate<String, DeviceDataDTO> kafkaTemplate;

    public void publishData(DeviceDataDTO data) {
        kafkaTemplate.send(TOPIC, data);
    }

}
