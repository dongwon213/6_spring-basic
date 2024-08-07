package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CarImageDTO {
    private int carId;
    private String storedFileName;
}
