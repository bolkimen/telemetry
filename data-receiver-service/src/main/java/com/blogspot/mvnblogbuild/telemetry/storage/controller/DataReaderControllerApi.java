package com.blogspot.mvnblogbuild.telemetry.storage.controller;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface DataReaderControllerApi {

    @RequestMapping("/healthCheck")
    String healthCheck();

    @RequestMapping(method = RequestMethod.PUT,
            value = "/reader",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    String readData(@Valid @RequestBody DeviceDataDTO data);
}
