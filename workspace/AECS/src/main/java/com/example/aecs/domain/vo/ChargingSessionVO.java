package com.example.aecs.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class ChargingSessionVO {
    private int sessionId;
    private int requestNumber;
    private String chargingStartTime;
    private String chargingEndTime;
    private int chargeAmount;

}
