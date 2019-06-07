package com.blogspot.mvnblogbuild.telemetry.storage;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataStorageApplication.class)
@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, controlledShutdown = false,
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class DataStorageControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EurekaClient eurekaClient;

    @Test
    public void testStats() throws Exception {
        DeviceDataDTO data = new DeviceDataDTO();
        data.setGroupName("group1");
        data.setSerialNumber("snumber1");
        data.setValue(1212l);

        ObjectMapper objectMapper = new ObjectMapper();

        Application app = new Application("APP");
        when(eurekaClient.getApplication(any())).thenReturn(app);

        mvc.perform(get("/stats"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"totalRecords\":0,\"averageValue\":0}"));
    }

}
