package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ChargingStationDTO {
    private int stationId;
    private String stationAddress;
    private String stationPhoneNumber;
    private String stationName;
    private Double latitude;
    private Double longitude;
}
