package com.example.aecs.domain.vo;

import com.example.aecs.domain.dto.CarDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class CarVO {

    private int carId;
    private String carNumber;
    private String carName;
    private String carModel;
    private String carChargingType;
    private String providerId;
    private int insurerId;
    private double currentLatitude;
    private double currentLongitude;
    private int currentBattery;
    private String currentStatus;
    private String carImageFileName;

    @Builder
    public CarVO(int carId, String carNumber, String carName, String carModel, String carChargingType, String providerId, int insurerId, double currentLatitude, double currentLongitude, int currentBattery, String currentStatus, String carImageFileName ){
        this.carId = carId;
        this.carNumber = carNumber;
        this.carName = carName;
        this.carModel = carModel;
        this.carChargingType = carChargingType;
        this.providerId = providerId;
        this.insurerId = insurerId;
        this.currentLatitude = currentLatitude;
        this.currentLongitude = currentLongitude;
        this.currentBattery = currentBattery;
        this.currentStatus = currentStatus;
        this.carImageFileName = carImageFileName;
    }

    public static CarVO toEntity(CarDTO carDTO){
        return CarVO.builder().carId(carDTO.getCarId())
                .carNumber(carDTO.getCarNumber())
                .carName(carDTO.getCarName())
                .carModel(carDTO.getCarModel())
                .carChargingType(carDTO.getCarChargingType())
                .providerId(carDTO.getProviderId())
                .insurerId(carDTO.getInsurerId())
                .currentLatitude(carDTO.getCurrentLatitude())
                .currentLongitude(carDTO.getCurrentLongitude())
                .currentBattery(carDTO.getCurrentBattery())
                .currentStatus(carDTO.getCurrentStatus())
                .carImageFileName(carDTO.getCarImageFileName())
                .build();
    }


}



