package com.blogspot.mvnblogbuild.telemetry.storage.service;

import com.blogspot.mvnblogbuild.telemetry.commons.dto.DeviceDataDTO;
import com.blogspot.mvnblogbuild.telemetry.storage.dto.DataStatsDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class StatsDataService {

    private DataStatsDTO dataStatsDTO = new DataStatsDTO();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();
    private Lock readLock = readWriteLock.readLock();

    public void updateStats(DeviceDataDTO data) {
        writeLock.lock();
        try {
            Long averageValue = (dataStatsDTO.getTotalRecords() * dataStatsDTO.getAverageValue() + data.getValue())
                    / (dataStatsDTO.getTotalRecords() + 1);
            dataStatsDTO.setAverageValue(averageValue);
            dataStatsDTO.setTotalRecords(dataStatsDTO.getTotalRecords() + 1);
        } finally {
            writeLock.unlock();
        }
    }

    public DataStatsDTO getStats() {
        DataStatsDTO result = null;

        readLock.lock();
        try {
            result = new DataStatsDTO(dataStatsDTO);
        } finally {
            readLock.unlock();
        }

        return result;
    }

}
