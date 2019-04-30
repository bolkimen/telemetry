package com.blogspot.mvnblogbuild.telemetry.storage.service;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class DeviceDataService {

    private Logger logger = LoggerFactory.getLogger(DeviceDataService.class);

    @Value(value = "${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String, DeviceDataDTO> kafkaTemplate;

    public void publishData(DeviceDataDTO data) {
        ListenableFuture<SendResult<String, DeviceDataDTO>> future = kafkaTemplate.send(kafkaTopic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, DeviceDataDTO>>() {

            @Override
            public void onSuccess(SendResult<String, DeviceDataDTO> result) {
                logger.info("Sent message=[" + data +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message=["
                        + data + "] due to : " + ex.getMessage());
            }
        });
    }

}
