package com.blogspot.mvnblogbuild.telemetry.storage.controller;

import com.blogspot.mvnblogbuild.telemetry.storage.dto.DataStatsDTO;
import com.blogspot.mvnblogbuild.telemetry.storage.service.StatsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataStorageController implements DataStorageControllerApi {

    @Autowired
    private StatsDataService statsDataService;

    @Override
    public DataStatsDTO readData() {
        return statsDataService.getStats();
    }
}
