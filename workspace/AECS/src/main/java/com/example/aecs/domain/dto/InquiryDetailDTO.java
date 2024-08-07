package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InquiryDetailDTO {
    private int inquiryId;
    private String inquiryTitle;
    private String inquiryContent;
    private int inquiryView;
    private String inquiryRegisterDate;
    private String inquiryUpdateDate;
    private String providerId;

    // 충전소
    private String station;

    // 문의 유형
    private String inquiryType;

    // 첨부파일의 수
    private int fileCount;

    // 사고유형
    private String accident;

    private String providerName;


}
