package com.example.aecs.mapper;

import com.example.aecs.domain.dto.OwnerDTO;
import com.example.aecs.domain.vo.OwnerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerMapper {

    OwnerDTO findByProviderId(String providerId);

    void saveOwner(OwnerVO vo);

    void updateOwner(OwnerVO vo);

    void insertNewOwner(OwnerVO vo);

    OwnerDTO selectOwner(String providerId);

}
