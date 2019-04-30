package com.blogspot.mvnblogbuild.telemetry.storage.controller;

import com.blogspot.mvnblogbuild.telemetry.storage.dto.DataStatsDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface DataStorageControllerApi {

    @RequestMapping(method = RequestMethod.GET,
            value = "/stats",
            produces = MediaType.APPLICATION_JSON_VALUE)
    DataStatsDTO readData();

}
