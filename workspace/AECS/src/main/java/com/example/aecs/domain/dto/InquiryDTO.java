package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryDTO {
    private int inquiryId;
    private String inquiryTitle;
    private String inquiryContent;
    private int inquiryView;
    private String inquiryRegisterDate;
    private String inquiryUpdateDate;
    private String providerId;
    private int requestId;
    private String accident;


}
