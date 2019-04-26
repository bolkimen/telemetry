package com.blogspot.mvnblogbuild.telemetry.commons.dto;

import lombok.Data;

@Data
public class DeviceDataDTO {
    private String groupName;
    private String serialNumber;
    private Long value;
}
