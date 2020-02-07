package com.blogspot.mvnblogbuild.telemetry.kafkatest;

import com.blogspot.mvnblogbuild.telemetry.kafkatest.dto.DeviceDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaTestApplication.class)
@EmbeddedKafka(partitions = 1, controlledShutdown = false,
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class DataReaderControllerIntegrationTest {

    @Test
    public void testReader() throws Exception {
        DeviceDataDTO data = new DeviceDataDTO();
        data.setGroupName("group1");
        data.setSerialNumber("snumber1");
        data.setValue(1212l);

        ObjectMapper objectMapper = new ObjectMapper();
    }

}
