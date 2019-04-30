package com.blogspot.mvnblogbuild.telemetry.storage;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import com.blogspot.mvnblogbuild.telemetry.receiver.DataReceiverApplication;
import com.blogspot.mvnblogbuild.telemetry.receiver.controller.DataReaderController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataReceiverApplication.class)
@AutoConfigureMockMvc
public class DataReaderControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DataReaderController dataReaderController;

    @MockBean
    private EurekaClient eurekaClient;

    @Test
    public void testReader() throws Exception {
        DeviceDataDTO data = new DeviceDataDTO();
        data.setGroupName("group1");
        data.setSerialNumber("snumber1");
        data.setValue(1212l);

        ObjectMapper objectMapper = new ObjectMapper();

        Application app = new Application("APP");
        when(eurekaClient.getApplication(any())).thenReturn(app);

        mvc.perform(put("/reader")
                .content(objectMapper.writeValueAsString(data))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(format("Receive data %s from '%s with Port Number %d'!",
                        data, app.getName(), dataReaderController.getApplicationId())));
    }

}
