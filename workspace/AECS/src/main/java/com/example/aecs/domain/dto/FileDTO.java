package com.example.aecs.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileDTO {
    private int fileId;
    private int inquiryId;
    private String originalFileName;
    private String storedFileName;
    private int fileSize;
    private String uploadTime;

}
