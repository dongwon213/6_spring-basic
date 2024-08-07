package com.example.aecs.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class DrivingInfoVO {
    private int drivingId;
    private int carId;
    private String startAddress;
    private int stationId;
    private String endAddress;
    private String startTime;
    private String endTime;
    private String drivingStatus;
}
