package com.example.aecs.service;

import com.example.aecs.domain.vo.ChargingStationVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChargingStationService {

    List<ChargingStationVO> getAllStations();
}
