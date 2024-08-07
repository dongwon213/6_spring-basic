package com.example.aecs.domain.vo;

import com.example.aecs.domain.dto.ChargingStationChargerDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class ChargingStationChargerVO {

    private int chargerId;
    private int stationId;
    private String carChargingType;
    private String chargerStatus;
    private String chargerType;

    @Builder
    public ChargingStationChargerVO(int chargerId, int stationId, String carChargingType, String chargerStatus ,String chargerType){
        this.chargerId = chargerId;
        this.stationId = stationId;
        this.carChargingType = carChargingType;
        this.chargerStatus = chargerType;
        this.chargerType = chargerType;
    }

    public static ChargingStationChargerVO toEntity(ChargingStationChargerDTO chargingStationChargerDTO){
        return ChargingStationChargerVO.builder()
                .chargerId(chargingStationChargerDTO.getChargerId())
                .stationId(chargingStationChargerDTO.getStationId())
                .carChargingType(chargingStationChargerDTO.getCarChargingType())
                .chargerStatus(chargingStationChargerDTO.getChargerStatus())
                .chargerType(chargingStationChargerDTO.getChargerType())
                .build();
    }




}
