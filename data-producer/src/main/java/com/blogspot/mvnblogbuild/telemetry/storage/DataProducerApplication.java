package com.blogspot.mvnblogbuild.telemetry.storage;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class DataProducerApplication {
    private static final Integer AMOUNT_OF_EMULATED_DEVICES = 3;

    public static void main(String[] args) throws JsonProcessingException {
        Request[] requests = new Request[AMOUNT_OF_EMULATED_DEVICES];

        ObjectMapper objectMapper = new ObjectMapper();

        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
                .setConnectTimeout(100);
        AsyncHttpClient client = Dsl.asyncHttpClient(clientBuilder);

        while (true) {
            long currentMilliseconds = System.currentTimeMillis();

            for (int i = 0; i < AMOUNT_OF_EMULATED_DEVICES; i++) {
                requests[i] = new RequestBuilder(HttpConstants.Methods.PUT)
                        .setUrl("http://localhost:8762/data-receiver-service/reader")
                        .setBody(objectMapper.writeValueAsString(createDeviceDataDTO(i)))
                        .setHeader("Content-Type", "application/json")
                        .build();
            }

            Stream.of(requests).forEach( request -> {
                        ListenableFuture<Response> listenableFuture = client.executeRequest(request);
                        listenableFuture.addListener(() -> {
                            try {
                                Response response = listenableFuture.get();
                                System.out.println(response.getStatusCode() + " Body: " + response.getResponseBody());
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                            }
                        }, Executors.newCachedThreadPool());
                    }
            );

            try {
                long sleepTimeMillis = 1000 - (System.currentTimeMillis() - currentMilliseconds);
                if (sleepTimeMillis > 0) {
                    Thread.sleep(sleepTimeMillis);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static DeviceDataDTO createDeviceDataDTO(int i) {
        DeviceDataDTO data = new DeviceDataDTO();
        data.setGroupName(i % 2 == 0 ? "group 1" : null);
        data.setSerialNumber("device-" + i);
        data.setValue(i * 100l);
        return data;
    }

}
