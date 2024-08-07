package com.example.aecs.mapper;

import com.example.aecs.domain.dto.CarDTO;
import com.example.aecs.domain.dto.CarLocationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarMapper {

    int getSeq();

    void registerCar(CarDTO carDTO);

    List<CarDTO> findCarsByProviderId(@Param("providerId") String providerId);

    CarLocationDTO getCurrentLocationByCarId(int carId);

    String getCarImageByCarId(int carId);
}
