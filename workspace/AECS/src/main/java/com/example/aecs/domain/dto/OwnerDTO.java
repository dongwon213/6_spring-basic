package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OwnerDTO {

    private String providerId;
    private String provider;
    private String name;
    private int age;
    private String licence;
    private String phoneNumber;
    private String address;
    private String ownerType;
    private String profilePic;
    private String role;

}
