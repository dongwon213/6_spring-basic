package com.example.aecs.service;

import com.example.aecs.domain.dto.OwnerDTO;
import com.example.aecs.exception.UnauthorizedException;
import com.example.aecs.mapper.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerMapper ownerMapper;

    @Override
    public OwnerDTO getOwnerByProviderId(String providerId) {
        OwnerDTO owner = ownerMapper.findByProviderId(providerId);
        if (owner == null) {
            throw new UnauthorizedException("Owner not found.");
        }
        return owner;
    }
}
