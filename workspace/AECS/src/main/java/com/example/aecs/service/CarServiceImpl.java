package com.example.aecs.service;

import com.example.aecs.domain.dto.CarDTO;
import com.example.aecs.domain.dto.CarLocationDTO;
import com.example.aecs.mapper.CarMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;

    public CarServiceImpl(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    @Override
    public void registerCar(CarDTO car, MultipartFile carImage) throws IOException {
        int carId = carMapper.getSeq();
        car.setCarId(carId);

        // 이미지 저장 및 파일 이름 설정
        if (carImage != null && !carImage.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads"; // 상대 경로로 설정
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
            String fileName = UUID.randomUUID().toString() + "_" + carImage.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            carImage.transferTo(filePath);
            car.setCarImageFileName(fileName);
        }

        carMapper.registerCar(car);
    }

    @Override
    public List<CarDTO> getCarsByProviderId(String providerId) {
        return carMapper.findCarsByProviderId(providerId);
    }

    @Override
    public CarLocationDTO getCurrentLocationByCarId(int carId) {
        return carMapper.getCurrentLocationByCarId(carId);
    }

    public String getCarImage(int carId) {
        return carMapper.getCarImageByCarId(carId);
    }
}
