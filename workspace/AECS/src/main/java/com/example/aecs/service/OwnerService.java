package com.example.aecs.service;

import com.example.aecs.domain.dto.OwnerDTO;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {

    OwnerDTO getOwnerByProviderId(String providerId) ;

}
