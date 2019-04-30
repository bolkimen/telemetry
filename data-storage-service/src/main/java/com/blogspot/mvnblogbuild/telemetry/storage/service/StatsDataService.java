package com.blogspot.mvnblogbuild.telemetry.storage.service;

import com.blogspot.mvnblogbuild.telemetry.storage.dto.DataStatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StatsDataService {

    private Logger logger = LoggerFactory.getLogger(StatsDataService.class);

    private DataStatsDTO dataStatsDTO = new DataStatsDTO();

    public DataStatsDTO getStats() {
        return dataStatsDTO;
    }

}
