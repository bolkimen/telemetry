package com.blogspot.mvnblogbuild.telemetry.commons.dto;

public class DeviceDataDTO {
    private String groupName;
    private String serialNumber;
    private Long value;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DeviceDataDTO[groupName=" + groupName +
                ", serialNumber=" + serialNumber +
                ", value=" + value + "]";
    }
}
