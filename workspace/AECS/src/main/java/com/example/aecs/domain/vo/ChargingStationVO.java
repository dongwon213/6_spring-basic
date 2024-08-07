package com.example.aecs.domain.vo;

import com.example.aecs.domain.dto.ChargingStationDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@ToString
@NoArgsConstructor
public class ChargingStationVO {
    private int stationId;
    private String stationAddress;
    private String stationPhoneNumber;
    private String stationName;
    private Double latitude;
    private Double longitude;
    private List<ChargingStationChargerVO> chargers;

    @Builder
    public ChargingStationVO(int stationId, String stationAddress, String stationPhoneNumber, String stationName, Double latitude, Double longitude, List<ChargingStationChargerVO> chargers) {
        this.stationId = stationId;
        this.stationAddress = stationAddress;
        this.stationPhoneNumber = stationPhoneNumber;
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.chargers = chargers;
    }

    public static ChargingStationVO toEntity(ChargingStationDTO chargingStationDTO, List<ChargingStationChargerVO> chargers) {
        return ChargingStationVO.builder()
                .stationId(chargingStationDTO.getStationId())
                .stationAddress(chargingStationDTO.getStationAddress())
                .stationPhoneNumber(chargingStationDTO.getStationPhoneNumber())
                .stationName(chargingStationDTO.getStationName())
                .latitude(chargingStationDTO.getLatitude())
                .longitude(chargingStationDTO.getLongitude())
                .chargers(chargers)
                .build();
    }
}
