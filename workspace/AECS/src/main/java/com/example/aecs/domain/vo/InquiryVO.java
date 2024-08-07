package com.example.aecs.domain.vo;

import com.example.aecs.domain.dto.InquiryDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@ToString
@NoArgsConstructor
public class InquiryVO {
    private int inquiryId;
    private String inquiryTitle;
    private String inquiryContent;
    private int inquiryView;
    private String inquiryRegisterDate;
    private String inquiryUpdateDate;
    private String providerId;
    private int requestId;
    private String accident;


    @Builder
    public InquiryVO(int inquiryId, String inquiryTitle, String inquiryContent, int inquiryView, String inquiryRegisterDate, String inquiryUpdateDate, String providerId, int requestId, String accident) {
        this.inquiryId = inquiryId;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryView = inquiryView;
        this.inquiryRegisterDate = inquiryRegisterDate;
        this.inquiryUpdateDate = inquiryUpdateDate;
        this.providerId = providerId;
        this.requestId = requestId;
        this.accident = accident;
    }

    public static InquiryVO toentity(InquiryDTO inquiryDTO) {
        return InquiryVO.builder().inquiryId(inquiryDTO.getInquiryId())
                .inquiryTitle(inquiryDTO.getInquiryTitle())
                .inquiryContent(inquiryDTO.getInquiryContent())
                .inquiryView(inquiryDTO.getInquiryView())
                .inquiryRegisterDate(inquiryDTO.getInquiryRegisterDate())
                .inquiryUpdateDate(inquiryDTO.getInquiryUpdateDate())
                .providerId(inquiryDTO.getProviderId())
                .requestId(inquiryDTO.getRequestId())
                .accident(inquiryDTO.getAccident())
                .build();
    }

}
