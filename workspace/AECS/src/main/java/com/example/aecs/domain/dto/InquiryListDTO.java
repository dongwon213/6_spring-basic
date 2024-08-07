package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryListDTO {

    private int inquiryId;
    private String inquiryTitle;
    private String ownerName;
    private String stationName;
    private int inquiryView;
    private String inquiryRegisterDate;
    private String inquiryUpdateDate;
    private String providerId;
    private int requestId;
    private int stationId;
    private int fileCount;


}
