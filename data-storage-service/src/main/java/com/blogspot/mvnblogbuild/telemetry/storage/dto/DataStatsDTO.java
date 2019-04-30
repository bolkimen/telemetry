package com.blogspot.mvnblogbuild.telemetry.storage.dto;

public class DataStatsDTO {

    private Long totalRecords = 0l;
    private Long averageValue = 0l;

    public DataStatsDTO() {}

    public DataStatsDTO(DataStatsDTO data) {
        this.averageValue = data.getAverageValue();
        this.totalRecords = data.getTotalRecords();
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Long getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Long averageValue) {
        this.averageValue = averageValue;
    }
}
