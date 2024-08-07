package com.example.aecs.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class StationChargerVO {
    private int chargerId;
    private int stationId;
    private String carChargingType;
    private String chargerStatus;
}
