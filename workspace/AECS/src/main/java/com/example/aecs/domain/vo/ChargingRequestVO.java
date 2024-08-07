package com.example.aecs.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class ChargingRequestVO {
    private int requestId;
    private String providerId;
    private int carId;
    private String chargingPermit;
    private int stationId;
    private String requestStatus;
    private String chargingTime;
    private int chargeAmount;
}
