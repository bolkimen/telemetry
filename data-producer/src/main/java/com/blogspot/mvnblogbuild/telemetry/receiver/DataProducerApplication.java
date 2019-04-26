package com.blogspot.mvnblogbuild.telemetry.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.*;
import org.asynchttpclient.util.HttpConstants;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class DataProducerApplication {
    private static final Integer AMOUNT_OF_EMULATED_DEVICES = 3;

    public static void main(String[] args) {
        Request[] requests = new Request[AMOUNT_OF_EMULATED_DEVICES];

        ObjectMapper objectMapper = new ObjectMapper();

        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
                .setConnectTimeout(500);
        AsyncHttpClient client = Dsl.asyncHttpClient(clientBuilder);

        while (true) {
            long currentMilliseconds = System.currentTimeMillis();

            for (int i = 0; i < AMOUNT_OF_EMULATED_DEVICES; i++) {
                requests[i] = new RequestBuilder(HttpConstants.Methods.POST)
                        .setUrl("http://www.baeldung.com")
                        .build();
            }

            Stream.of(requests).forEach( request -> {
                        ListenableFuture<Response> listenableFuture = client.executeRequest(request);
                        listenableFuture.addListener(() -> {
                            try {
                                Response response = listenableFuture.get();
                                System.out.println(response.getStatusCode());
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

}
