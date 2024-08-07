package com.example.aecs.service;

import com.example.aecs.domain.vo.ChargingStationVO;
import com.example.aecs.mapper.ChargingStationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargingStationServiceImpl implements ChargingStationService {

    private final ChargingStationMapper chargingStationMapper;

    @Override
    public List<ChargingStationVO> getAllStations() {
        return chargingStationMapper.getAllStations();
    }
}
