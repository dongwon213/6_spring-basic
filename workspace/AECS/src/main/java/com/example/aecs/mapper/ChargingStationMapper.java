package com.example.aecs.mapper;

import com.example.aecs.domain.vo.ChargingStationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChargingStationMapper {
    List<ChargingStationVO> getAllStations();
}
