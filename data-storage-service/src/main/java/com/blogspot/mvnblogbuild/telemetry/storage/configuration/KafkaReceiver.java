package com.blogspot.mvnblogbuild.telemetry.storage.configuration;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import com.blogspot.mvnblogbuild.telemetry.storage.service.StatsDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiver {

    private Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Value(value = "${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    private StatsDataService statsDataService;

    @KafkaListener(topics = "${kafka.topic}")
    public void listenWithHeaders(
            @Payload DeviceDataDTO data,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info("Received Data: " + data + " from partition: " + partition);
    }

}
