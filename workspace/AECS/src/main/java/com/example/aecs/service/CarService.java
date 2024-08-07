package com.example.aecs.service;

import com.example.aecs.domain.dto.CarDTO;
import com.example.aecs.domain.dto.CarLocationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void registerCar(CarDTO car, MultipartFile carImage) throws IOException;

    List<CarDTO> getCarsByProviderId(String providerId);

    CarLocationDTO getCurrentLocationByCarId(int carId);

    String getCarImage(int carId); // 이미지 경로 조회 메서드 수정
}
