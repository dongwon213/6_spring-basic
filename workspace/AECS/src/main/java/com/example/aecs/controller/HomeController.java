package com.example.aecs.controller;

import com.example.aecs.domain.vo.ChargingStationVO;
import com.example.aecs.service.ChargingStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final ChargingStationService chargingStationService;

    @GetMapping
    public String index() {
        return "home";
    }


    @GetMapping("/stations")
    @ResponseBody
    public List<ChargingStationVO> getAllStations() {
        return chargingStationService.getAllStations();
    }
}
