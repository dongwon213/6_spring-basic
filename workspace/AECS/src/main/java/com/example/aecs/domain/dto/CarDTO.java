package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CarDTO {
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
}
