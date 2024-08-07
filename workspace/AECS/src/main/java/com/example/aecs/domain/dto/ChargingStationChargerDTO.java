package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ChargingStationChargerDTO {
    private int chargerId;
    private int stationId;
    private String carChargingType;
    private String chargerStatus;
    private String chargerType;

}
