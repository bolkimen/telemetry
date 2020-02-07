package com.blogspot.mvnblogbuild.telemetry.kafkatest.service;

import com.blogspot.mvnblogbuild.telemetry.kafkatest.dto.DeviceDataDTO;

public interface StarshipService {

    void send(DeviceDataDTO dto);

    void consume(DeviceDataDTO dto);

}
