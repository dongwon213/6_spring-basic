package com.example.aecs.domain.vo;

import com.example.aecs.domain.dto.OwnerDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class OwnerVO {

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

    @Builder
    public OwnerVO(String providerId, String provider, String name, int age, String licence, String phoneNumber, String address, String ownerType , String profilePic, String role){
        this.providerId = providerId;
        this.provider = provider;
        this.name = name;
        this.age = age;
        this.licence = licence;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.ownerType = ownerType;
        this.profilePic = profilePic;
        this.role = role;
    }

    public static OwnerVO toEntity(OwnerDTO dto){
        return OwnerVO.builder().providerId(dto.getProviderId())
                .provider(dto.getProvider())
                .name(dto.getName())
                .age(dto.getAge())
                .licence(dto.getLicence())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .ownerType(dto.getOwnerType())
                .profilePic(dto.getProfilePic())
                .role(dto.getRole())
                .build();
    }



}
