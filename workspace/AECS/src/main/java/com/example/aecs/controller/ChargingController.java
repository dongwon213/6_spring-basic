package com.example.aecs.controller;

import com.example.aecs.domain.dto.CarLocationDTO;
import com.example.aecs.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/map")
@RequiredArgsConstructor
public class ChargingController {

    private final CarService carService;

    @GetMapping
    public String index(){
        return "map";
    }

    @GetMapping("/car/location")
    @ResponseBody
    public CarLocationDTO getCurrentLocation(@RequestParam int carId) {
        return carService.getCurrentLocationByCarId(carId);
    }


}
